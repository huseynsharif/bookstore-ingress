package com.example.bookstorebackend.entities.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StudentRequestDTO {

    private String name;
    private int age;
    private int userId;

}
