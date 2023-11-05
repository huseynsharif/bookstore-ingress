package com.example.bookstorebackend.api.controllers;

import com.example.bookstorebackend.business.abstracts.AuthorService;
import com.example.bookstorebackend.entities.dtos.request.AuthorRequestDTO;
import com.example.bookstorebackend.entities.dtos.request.NewBookRequestDTO;
import com.example.bookstorebackend.entities.dtos.request.StudentRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody AuthorRequestDTO authorRequestDTO){

        return ResponseEntity.ok(this.authorService.add(authorRequestDTO));

    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody AuthorRequestDTO authorRequestDTO){

        return ResponseEntity.ok(this.authorService.update(authorRequestDTO));

    }

    @PostMapping("/add-new-book")
    public ResponseEntity<?> addNewBook(@RequestBody NewBookRequestDTO newBookRequestDTO){

        return ResponseEntity.ok(this.authorService.addNewBook(newBookRequestDTO));

    }

    @DeleteMapping("/remove-book-by-book-id")
    public ResponseEntity<?> removeBookByBookId(@RequestParam("bookId") int bookId, @RequestParam("authorId") int authorId){

        return ResponseEntity.ok(this.authorService.removeBookByBookId(bookId, authorId));

    }


    @PostMapping("/getall-by-author-id")
    public ResponseEntity<?> getAllBookByAuthorId(@RequestParam int authorId){

        return ResponseEntity.ok(this.authorService.getAllBookByAuthorId(authorId));

    }
}
