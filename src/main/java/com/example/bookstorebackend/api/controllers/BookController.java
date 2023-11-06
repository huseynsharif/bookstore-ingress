package com.example.bookstorebackend.api.controllers;


import com.example.bookstorebackend.business.abstracts.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("get-all-by-name-ignore-case")
    public ResponseEntity<?> getAllByNameContainsIgnoreCase(@RequestParam String name){
        return ResponseEntity.ok(this.bookService.getAllByNameContainsIgnoreCase(name));
    }

}
