package com.example.bookstorebackend.api.controllers;

import com.example.bookstorebackend.business.abstracts.StudentService;
import com.example.bookstorebackend.entities.dtos.request.StudentRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody StudentRequestDTO studentRequestDTO){

        return ResponseEntity.ok(this.studentService.add(studentRequestDTO));

    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody StudentRequestDTO studentRequestDTO){

        return ResponseEntity.ok(this.studentService.update(studentRequestDTO));

    }

}
