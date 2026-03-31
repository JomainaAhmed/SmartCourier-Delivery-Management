package com.capg.tracking.event;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TrackingEvent implements Serializable {

    private Long deliveryId;
    private String status;

    public TrackingEvent() {}

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