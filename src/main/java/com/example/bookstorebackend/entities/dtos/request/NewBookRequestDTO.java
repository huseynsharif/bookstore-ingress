package com.example.bookstorebackend.entities.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class NewBookRequestDTO {

    private String name;
    private int authorId;

}
