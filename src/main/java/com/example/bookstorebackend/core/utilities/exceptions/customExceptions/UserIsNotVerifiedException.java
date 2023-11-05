package com.example.bookstorebackend.core.utilities.exceptions.customExceptions;

public class UserIsNotVerifiedException extends RuntimeException{
    public UserIsNotVerifiedException(String message) {
        super(message);
    }
}
