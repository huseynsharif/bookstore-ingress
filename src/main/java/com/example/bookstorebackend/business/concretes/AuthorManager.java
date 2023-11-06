package com.example.bookstorebackend.business.concretes;

import com.example.bookstorebackend.business.abstracts.AuthorService;
import com.example.bookstorebackend.core.utilities.exceptions.customExceptions.IllegalDeletionRequestException;
import com.example.bookstorebackend.core.utilities.exceptions.customExceptions.NotFoundException;
import com.example.bookstorebackend.core.utilities.results.DataResult;
import com.example.bookstorebackend.core.utilities.results.Result;
import com.example.bookstorebackend.core.utilities.results.SuccessDataResult;
import com.example.bookstorebackend.core.utilities.results.SuccessResult;
import com.example.bookstorebackend.dataAccess.abstracts.AuthorDAO;
import com.example.bookstorebackend.dataAccess.abstracts.BookDAO;
import com.example.bookstorebackend.dataAccess.abstracts.UserDAO;
import com.example.bookstorebackend.entities.Author;
import com.example.bookstorebackend.entities.Book;
import com.example.bookstorebackend.entities.User;
import com.example.bookstorebackend.entities.dtos.request.AuthorRequestDTO;
import com.example.bookstorebackend.entities.dtos.request.NewBookRequestDTO;
import com.example.bookstorebackend.entities.dtos.response.BookResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorManager implements AuthorService {

    private final UserDAO userDAO;
    private final AuthorDAO authorDAO;
    private final BookDAO bookDAO;


    @Override
    public Result add(AuthorRequestDTO authorRequestDTO) {

        User user = this.userDAO.findById(authorRequestDTO.getUserId())
                .orElseThrow(()->new NotFoundException("Cannot find user with given id: " + authorRequestDTO.getUserId()));

        Author newAuthor = new Author(
            authorRequestDTO.getName(),
            authorRequestDTO.getAge(),
            user
        );
        this.authorDAO.save(newAuthor);
        return new SuccessResult("Author was saved successfully");
    }

    @Override
    public Result update(AuthorRequestDTO authorRequestDTO) {

        Author author = this.authorDAO.findAuthorByUser_Id(authorRequestDTO.getUserId());
        if (author == null) {
            throw new NotFoundException("Cannot find author with userId: " + authorRequestDTO.getUserId());
        }

        author.setName(authorRequestDTO.getName());
        author.setAge(authorRequestDTO.getAge());
        this.authorDAO.save(author);
        return new SuccessResult("Author updated successfully.");

    }

    @Override
    public Result addNewBook(NewBookRequestDTO newBookRequestDTO) {

        Author author = this.authorDAO.findById(newBookRequestDTO.getAuthorId()).orElseThrow(
                ()-> new NotFoundException("Cannot find author with given authorId: " + newBookRequestDTO.getAuthorId())
        );

        Book book = new Book(
                newBookRequestDTO.getName(),
                author
        );

        this.bookDAO.save(book);
        return new SuccessResult("New book successfully saved");
    }

    @Override
    public Result removeBookByBookId(int bookId, int authorId) {

        Book book = this.bookDAO.findById(bookId).orElseThrow(
                    ()-> new NotFoundException("Cannot find book with given bookId: "+bookId)
                );

        if (book.getAuthor().getId() != authorId){
            throw new IllegalDeletionRequestException("Cannot delete this book.");
        }

        this.bookDAO.deleteById(bookId);
        return new SuccessResult("Successfully deleted.");
    }

    @Override
    public DataResult<List<BookResponseDTO>> getAllBookByAuthorId(int authorId) {

        List<Book> books = this.bookDAO.getAllByAuthor_Id(authorId);

        if (books.isEmpty()){
            throw new NotFoundException("Cannot find any book with authorId: " + authorId);
        }

        List<BookResponseDTO> response = books.stream().map(
                (book -> new BookResponseDTO(
                        book.getId(),
                        book.getName(),
                        book.getAuthor().getName()
                ))
        ).toList();

        return new SuccessDataResult<>(response, "Successfully listed.");
    }

}
