package com.example.bookstorebackend.api.controllers;

import com.example.bookstorebackend.business.abstracts.AuthorService;
import com.example.bookstorebackend.entities.dtos.request.AuthorRequestDTO;
import com.example.bookstorebackend.entities.dtos.request.StudentRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
