package com.example.bookstorebackend.business.abstracts;

import com.example.bookstorebackend.core.utilities.results.DataResult;
import com.example.bookstorebackend.core.utilities.results.Result;
import com.example.bookstorebackend.entities.dtos.request.LoginRequestDTO;
import com.example.bookstorebackend.entities.dtos.request.SignUpRequestDTO;
import com.example.bookstorebackend.entities.dtos.response.UserLoginResponseDTO;

public interface UserService {

    Result signUp(SignUpRequestDTO signUpRequestDTO);

    DataResult<UserLoginResponseDTO> logIn(LoginRequestDTO loginRequestDTO);

    Result verifyAccountWithLink(int userId, String token);

}
