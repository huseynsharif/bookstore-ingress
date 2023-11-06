package com.example.bookstorebackend.core.utilities.exceptions.customExceptions;

public class AlreadyReadException extends RuntimeException{
    public AlreadyReadException(String message) {
        super(message);
    }
}
