package com.example.bookstorebackend.dataAccess.abstracts;

import com.example.bookstorebackend.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorDAO extends JpaRepository<Author, Integer> {

    Author findAuthorByUser_Id(int userId);
    Author findAuthorById(int authorId);

    boolean existsAuthorById(int authorId);

}
