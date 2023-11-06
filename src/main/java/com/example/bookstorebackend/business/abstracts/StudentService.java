package com.example.bookstorebackend.business.abstracts;

import com.example.bookstorebackend.core.utilities.results.DataResult;
import com.example.bookstorebackend.core.utilities.results.Result;

import com.example.bookstorebackend.entities.dtos.request.StudentRequestDTO;
import com.example.bookstorebackend.entities.dtos.response.BookResponseDTO;

import java.util.List;

public interface StudentService {

    Result add(StudentRequestDTO studentRequestDTO);

    Result update(StudentRequestDTO studentRequestDTO);

    Result readBook(int bookId, int studentId);

    Result finishBook(int bookId, int studentId);

    DataResult<List<BookResponseDTO>> getAllCurrentlyReadingsByStudentId(int studentId);

    Result subscribe(int studentId, int authorId);

}
