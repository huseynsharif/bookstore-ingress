package com.example.bookstorebackend.business.abstracts;

import com.example.bookstorebackend.core.utilities.results.DataResult;
import com.example.bookstorebackend.entities.Book;
import com.example.bookstorebackend.entities.dtos.response.BookResponseDTO;

import java.util.List;

public interface BookService {

    DataResult<List<BookResponseDTO>> getAllByNameContainsIgnoreCase(String name);

}
