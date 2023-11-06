package com.example.bookstorebackend.dataAccess.abstracts;


import com.example.bookstorebackend.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDAO extends JpaRepository<Book, Integer> {

    boolean existsBookById(int id);

    List<Book> getAllByAuthor_Id(int authorId);

    List<Book> getAllByNameContainsIgnoreCase(String name);
}
