package com.capg.delivery.producer;

import com.capg.delivery.event.TrackingEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class TrackingProducer {

    private final RabbitTemplate rabbitTemplate;

    public TrackingProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendTrackingEvent(TrackingEvent event) {
        rabbitTemplate.convertAndSend(
                "delivery-exchange",
                "tracking.key",
                event
        );
    }
}