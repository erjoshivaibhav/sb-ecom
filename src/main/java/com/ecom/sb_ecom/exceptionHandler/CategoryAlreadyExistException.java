package com.ecom.sb_ecom.exceptionHandler;

public class CategoryAlreadyExistException extends RuntimeException {
    public CategoryAlreadyExistException(String message) {
        super(message);
    }
}
