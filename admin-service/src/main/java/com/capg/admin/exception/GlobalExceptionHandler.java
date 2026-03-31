package com.capg.admin.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(org.springframework.security.authorization.AuthorizationDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(Exception ex) {

        ErrorResponse error = new ErrorResponse("Access Denied", 403);
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustom(CustomException ex) {

        ErrorResponse error = new ErrorResponse(ex.getMessage(), 400);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {

        ErrorResponse error = new ErrorResponse("Internal Server Error", 500);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(feign.FeignException.class)
    public ResponseEntity<ErrorResponse> handleFeignException(feign.FeignException ex) {
        
        ErrorResponse error = new ErrorResponse(ex.getMessage(), ex.status() > 0 ? ex.status() : 500);
        return new ResponseEntity<>(error, HttpStatus.valueOf(ex.status() > 0 ? ex.status() : 500));
    }
}