package com.example.kwordpocket.user.exception;

import com.example.kwordpocket.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class DuplicateEmailException extends CustomException {
    public DuplicateEmailException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}