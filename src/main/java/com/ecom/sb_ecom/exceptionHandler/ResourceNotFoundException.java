package com.ecom.sb_ecom.exceptionHandler;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
