package com.example.bookstorebackend.business.concretes;

import com.example.bookstorebackend.business.abstracts.AuthorService;
import com.example.bookstorebackend.core.utilities.exceptions.customExceptions.NotFoundException;
import com.example.bookstorebackend.core.utilities.results.Result;
import com.example.bookstorebackend.core.utilities.results.SuccessResult;
import com.example.bookstorebackend.dataAccess.abstracts.AuthorDAO;
import com.example.bookstorebackend.dataAccess.abstracts.UserDAO;
import com.example.bookstorebackend.entities.Author;
import com.example.bookstorebackend.entities.Student;
import com.example.bookstorebackend.entities.User;
import com.example.bookstorebackend.entities.dtos.request.AuthorRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorManager implements AuthorService {

    private final UserDAO userDAO;
    private final AuthorDAO authorDAO;


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

}
