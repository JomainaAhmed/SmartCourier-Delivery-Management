package com.capg.admin.exception;

@SuppressWarnings("serial")
public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }
}