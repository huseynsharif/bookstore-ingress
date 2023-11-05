package com.example.bookstorebackend.core.utilities.exceptions.customExceptions;

public class IllegalDeletionRequestException extends RuntimeException{
    public IllegalDeletionRequestException(String message) {
        super(message);
    }
}
