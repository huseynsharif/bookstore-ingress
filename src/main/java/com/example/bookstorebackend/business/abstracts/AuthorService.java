package com.example.bookstorebackend.business.abstracts;

import com.example.bookstorebackend.core.utilities.results.Result;
import com.example.bookstorebackend.entities.dtos.request.AuthorRequestDTO;

public interface AuthorService {

    Result add(AuthorRequestDTO authorRequestDTO);

    Result update(AuthorRequestDTO authorRequestDTO);

}
