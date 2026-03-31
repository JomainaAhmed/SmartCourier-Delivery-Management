package com.capg.delivery.exception;

@SuppressWarnings("serial")
public class DeliveryNotFoundException extends RuntimeException {

    public DeliveryNotFoundException(String message) {
        super(message);
    }
}