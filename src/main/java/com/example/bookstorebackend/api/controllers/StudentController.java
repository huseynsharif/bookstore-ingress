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

    @PostMapping("/read-book")
    public ResponseEntity<?> readBook(@RequestParam("bookId") int bookId, @RequestParam("studentId") int studentId){

        return ResponseEntity.ok(this.studentService.readBook(bookId, studentId));

    }

    @PostMapping("/finish-book")
    public ResponseEntity<?> finishBook(@RequestParam("bookId") int bookId, @RequestParam("studentId") int studentId){

        return ResponseEntity.ok(this.studentService.finishBook(bookId, studentId));

    }

    @GetMapping("/get-all-currently-readings-by-student-id")
    public ResponseEntity<?> getAllCurrentlyReadingsByStudentId(@RequestParam int studentId){
        return ResponseEntity.ok(this.studentService.getAllCurrentlyReadingsByStudentId(studentId));
    }

    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(@RequestParam("studentId") int studentId, @RequestParam("authorId") int authorId){

        return ResponseEntity.ok(this.studentService.subscribe(studentId, authorId));

    }



}
