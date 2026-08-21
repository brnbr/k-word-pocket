package com.example.kwordpocket.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignupRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(max = 12, message = "비밀번호는 최대 12자까지 입력할 수 있습니다.")
    private String password;
}
