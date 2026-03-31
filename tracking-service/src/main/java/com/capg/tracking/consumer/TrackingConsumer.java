package com.capg.tracking.consumer;

import com.capg.tracking.event.TrackingEvent;
import com.capg.tracking.service.TrackingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class TrackingConsumer {

    private static final Logger logger = LoggerFactory.getLogger(TrackingConsumer.class);
    private final TrackingService service;

    public TrackingConsumer(TrackingService service) {
        this.service = service;
    }

    @RabbitListener(queues = "tracking-queue")
    public void consume(TrackingEvent event) {

        logger.info("Received tracking update for delivery ID: {} with status: {}", 
            event.getDeliveryId(), event.getStatus());

        try {
            service.saveTracking(event.getDeliveryId(), event.getStatus());
            logger.info("Successfully persisted tracking for delivery ID: {}", event.getDeliveryId());
        } catch (Exception e) {
            logger.error("Error persisting tracking for delivery ID: {}", event.getDeliveryId(), e);
        }
    }
}