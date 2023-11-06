package com.example.bookstorebackend.entities.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookResponseDTO {

    private int id;
    private String bookName;
    private String authorName;

}
