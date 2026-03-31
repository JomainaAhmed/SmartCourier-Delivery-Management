package com.capg.delivery.exception;

@SuppressWarnings("serial")
public class InvalidStatusException extends RuntimeException {

    public InvalidStatusException(String message) {
        super(message);
    }
}