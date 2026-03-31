package com.capg.delivery.controller;

import com.capg.delivery.dto.AdminResponse;
import com.capg.delivery.dto.Request;
import com.capg.delivery.dto.Response;
import com.capg.delivery.service.DeliveryService;

import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryService service;

    public DeliveryController(DeliveryService service) {
        this.service = service;
    }

    // USER → Create delivery
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/create")
    public Response create(@Valid @RequestBody Request dto) {
        return service.createDelivery(dto);
    }

    // USER + ADMIN → Get delivery by ID
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public Response get(@PathVariable("id") Long id) {
        return service.getDelivery(id);
    }

    // ADMIN → Update delivery status
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/status")
    public Response updateStatus(@PathVariable("id") Long id,
                                @RequestParam("status") String status) {
        return service.updateStatus(id, status);
    }

    // ADMIN → Get all deliveries
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public List<AdminResponse> getAll() {
        return service.getAllDeliveriesDetailed();
    }

}