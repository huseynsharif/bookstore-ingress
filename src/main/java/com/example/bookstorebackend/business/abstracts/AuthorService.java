package com.example.bookstorebackend.business.abstracts;

import com.example.bookstorebackend.core.utilities.results.DataResult;
import com.example.bookstorebackend.core.utilities.results.Result;
import com.example.bookstorebackend.entities.dtos.request.AuthorRequestDTO;
import com.example.bookstorebackend.entities.dtos.request.NewBookRequestDTO;
import com.example.bookstorebackend.entities.dtos.response.BookResponseDTO;

import java.util.List;

public interface AuthorService {

    Result add(AuthorRequestDTO authorRequestDTO);

    Result update(AuthorRequestDTO authorRequestDTO);

    Result addNewBook(NewBookRequestDTO newBookRequestDTO);

    Result removeBookByBookId(int bookId, int authorId);

    DataResult<List<BookResponseDTO>> getAllBookByAuthorId(int authorId);

}
