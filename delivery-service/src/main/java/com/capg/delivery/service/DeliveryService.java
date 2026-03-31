package com.capg.delivery.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;

import com.capg.delivery.dto.AdminResponse;
import com.capg.delivery.dto.Request;
import com.capg.delivery.dto.Response;
import com.capg.delivery.entity.Delivery;
import com.capg.delivery.entity.PackageEntity;
import com.capg.delivery.enums.DeliveryStatus;
import com.capg.delivery.exception.DeliveryNotFoundException;
import com.capg.delivery.exception.InvalidStatusException;
import com.capg.delivery.mapper.DeliveryMapper;
import com.capg.delivery.repository.DeliveryRepository;
import com.capg.delivery.config.RabbitMQConfig;
import com.capg.delivery.dto.TrackingMessage;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
public class DeliveryService {

    private final DeliveryRepository repository;
    private final RabbitTemplate rabbitTemplate;

    public DeliveryService(DeliveryRepository repository, RabbitTemplate rabbitTemplate) {
        this.repository = repository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Response createDelivery(Request dto) {

        Delivery delivery = DeliveryMapper.toEntity(dto);

        PackageEntity pkg = delivery.getPackageEntity();

        double volumetricWeight = (pkg.getLength() * pkg.getWidth() * pkg.getHeight()) / 5000.0;
        double chargeableWeight = Math.max(pkg.getWeight(), volumetricWeight);

        double baseRate = 300;
        double price = chargeableWeight * baseRate;

        pkg.setPrice(price);

        Delivery savedDelivery = repository.save(delivery);

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                new TrackingMessage(savedDelivery.getId(), savedDelivery.getStatus().name())
        );

        return DeliveryMapper.toDTO(savedDelivery);
    }

    public Response getDelivery(Long id) {
        Delivery delivery = repository.findById(id)
                .orElseThrow(() -> new DeliveryNotFoundException("Delivery not found with id: " + id));

        return DeliveryMapper.toDTO(delivery);
    }

    public List<Response> getAllDeliveries() {
        return repository.findAll()
                .stream()
                .map(DeliveryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Response updateStatus(Long id, String status) {

        Delivery delivery = repository.findById(id)
                .orElseThrow(() -> new DeliveryNotFoundException("Delivery not found with id: " + id));

        DeliveryStatus newStatus;

        try {
            newStatus = DeliveryStatus.valueOf(status.toUpperCase());
        } catch (Exception e) {
            throw new InvalidStatusException("Invalid status value: " + status);
        }

        boolean isAdmin = SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isValidTransition(delivery.getStatus(), newStatus, isAdmin)) {
            throw new InvalidStatusException(
                    "Invalid status transition from " + delivery.getStatus() + " to " + newStatus
            );
        }

        delivery.setStatus(newStatus);
        Delivery saved = repository.save(delivery);

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                new TrackingMessage(saved.getId(), saved.getStatus().name())
        );

        return DeliveryMapper.toDTO(saved);
    }

    private boolean isValidTransition(DeliveryStatus current, DeliveryStatus next, boolean isAdmin) {

        Set<DeliveryStatus> adminStates = Set.of(
                DeliveryStatus.DELAYED,
                DeliveryStatus.RETURNED,
                DeliveryStatus.FAILED,
                DeliveryStatus.DELIVERED
        );

        if (isAdmin && adminStates.contains(next)) {
            return true;
        }

        switch (current) {
            case DRAFT:
                return next == DeliveryStatus.BOOKED;

            case BOOKED:
                return next == DeliveryStatus.PICKED_UP;

            case PICKED_UP:
                return next == DeliveryStatus.IN_TRANSIT;

            case IN_TRANSIT:
                return next == DeliveryStatus.OUT_FOR_DELIVERY;

            case OUT_FOR_DELIVERY:
                return next == DeliveryStatus.DELIVERED;

            case DELIVERED:
            case FAILED:
                return next == DeliveryStatus.RETURNED;

            case RETURNED:
                return false;

            case DELAYED:
                return next == DeliveryStatus.IN_TRANSIT || next == DeliveryStatus.OUT_FOR_DELIVERY;

            default:
                return false;
        }
    }

    public List<AdminResponse> getAllDeliveriesDetailed() {
        return repository.findAll()
                .stream()
                .map(DeliveryMapper::toAdminDTO)
                .toList();
    }
}