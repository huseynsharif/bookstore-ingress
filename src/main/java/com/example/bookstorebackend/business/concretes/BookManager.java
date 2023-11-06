package com.example.bookstorebackend.business.concretes;

import com.example.bookstorebackend.business.abstracts.BookService;
import com.example.bookstorebackend.core.utilities.exceptions.customExceptions.NotFoundException;
import com.example.bookstorebackend.core.utilities.results.DataResult;
import com.example.bookstorebackend.core.utilities.results.SuccessDataResult;
import com.example.bookstorebackend.dataAccess.abstracts.BookDAO;
import com.example.bookstorebackend.entities.Book;
import com.example.bookstorebackend.entities.dtos.response.BookResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookManager implements BookService {

    private final BookDAO bookDAO;

    @Override
    public DataResult<List<BookResponseDTO>> getAllByNameContainsIgnoreCase(String name) {

        List<Book> books = this.bookDAO.getAllByNameContainsIgnoreCase(name);

        if (books.isEmpty()) {
            throw new NotFoundException("Cannot find books with: " + name);
        }

        List<BookResponseDTO> response = books.stream().map(
                (book -> new BookResponseDTO(
                        book.getId(),
                        book.getName(),
                        book.getAuthor().getName()
                ))
        ).toList();

        return new SuccessDataResult<>(response, "Books listed.");
    }
}
