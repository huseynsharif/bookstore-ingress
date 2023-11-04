package com.example.bookstorebackend.core.utilities.exceptions.customExceptions;

public class TokenExpiredException extends RuntimeException{

    public TokenExpiredException(String message) {
        super(message);
    }
}
