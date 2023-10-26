package com.holiday.home.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException() {
        super("Resource not found exception occurs");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
