package com.example.bookstorebackend.core.utilities.exceptions.customExceptions;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
