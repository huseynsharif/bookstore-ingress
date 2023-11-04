package com.example.bookstorebackend.entities.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponseDTO {

    private int id;
    private String username;
    private String jwt;
    private List<String> roles;

}
