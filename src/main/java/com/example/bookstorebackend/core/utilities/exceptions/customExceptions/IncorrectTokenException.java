package com.example.bookstorebackend.core.utilities.exceptions.customExceptions;

public class IncorrectTokenException extends RuntimeException{
    public IncorrectTokenException(String message) {
        super(message);
    }
}
