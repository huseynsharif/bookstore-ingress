package com.example.bookstorebackend.business.abstracts;

import com.example.bookstorebackend.core.utilities.results.Result;

import com.example.bookstorebackend.entities.dtos.request.StudentRequestDTO;

public interface StudentService {

    Result add(StudentRequestDTO studentRequestDTO);

    Result update(StudentRequestDTO studentRequestDTO);

    Result readBook(int bookId);

}
