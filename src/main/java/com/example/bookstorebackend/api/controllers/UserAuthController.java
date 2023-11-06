package com.example.bookstorebackend.api.controllers;

import com.example.bookstorebackend.business.abstracts.UserService;
import com.example.bookstorebackend.entities.dtos.request.LoginRequestDTO;
import com.example.bookstorebackend.entities.dtos.request.SignUpRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO){

        return ResponseEntity.ok(this.userService.signUp(signUpRequestDTO));

    }

    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody LoginRequestDTO loginRequestDTO){

        return ResponseEntity.ok(this.userService.logIn(loginRequestDTO));

    }

    @GetMapping("/verify-account-with-link")
    public ResponseEntity<?> verifyAccountWithLink(@RequestParam("userId") int userId, @RequestParam("token") String token){

        return ResponseEntity.ok(this.userService.verifyAccountWithLink(userId, token));

    }


}
