package com.example.bookstorebackend.core.utilities.exceptions.customExceptions;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
