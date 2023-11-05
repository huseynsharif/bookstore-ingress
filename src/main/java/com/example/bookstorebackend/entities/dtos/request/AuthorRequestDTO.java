package com.example.bookstorebackend.entities.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthorRequestDTO {

    private String name;
    private int age;
    private int userId;

}
