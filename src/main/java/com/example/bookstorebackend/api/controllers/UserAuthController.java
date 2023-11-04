package com.example.bookstorebackend.api.controllers;

import com.example.bookstorebackend.business.abstracts.UserService;
import com.example.bookstorebackend.entities.dtos.request.LoginRequestDTO;
import com.example.bookstorebackend.entities.dtos.request.SignUpRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class UserAuthController {

    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO){

        return ResponseEntity.ok(this.userService.signUp(signUpRequestDTO));

    }

    @PostMapping("/login")
    public ResponseEntity<?> signUp(@RequestBody LoginRequestDTO loginRequestDTO){

        return ResponseEntity.ok(this.userService.logIn(loginRequestDTO));

    }


}
