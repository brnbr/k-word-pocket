package com.example.kwordpocket.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignupRequest {

    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String role;
}
