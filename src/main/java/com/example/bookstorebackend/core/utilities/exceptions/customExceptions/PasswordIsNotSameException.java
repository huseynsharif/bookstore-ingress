package com.example.bookstorebackend.core.utilities.exceptions.customExceptions;

public class PasswordIsNotSameException extends RuntimeException{

    public PasswordIsNotSameException(String message) {
        super(message);
    }
}