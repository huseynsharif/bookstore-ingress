package com.example.bookstorebackend.core.utilities.exceptions.customExceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
