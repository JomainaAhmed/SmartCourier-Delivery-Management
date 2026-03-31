package com.capg.delivery.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TrackingMessage implements Serializable {

    private Long deliveryId;
    private String status;

    public TrackingMessage() {}

    public TrackingMessage(Long deliveryId, String status) {
        this.deliveryId = deliveryId;
        this.status = status;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}