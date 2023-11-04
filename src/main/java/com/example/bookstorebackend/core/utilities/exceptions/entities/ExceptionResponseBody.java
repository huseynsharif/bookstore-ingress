package com.example.bookstorebackend.core.utilities.exceptions.entities;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ExceptionResponseBody {

    private int status;
    private String message;
    private LocalDateTime dateTime;

}
