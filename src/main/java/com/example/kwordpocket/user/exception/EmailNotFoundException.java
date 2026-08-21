package com.example.kwordpocket.user.exception;

import org.springframework.http.HttpStatus;

public class EmailNotFoundException extends ServiceException {
    public EmailNotFoundException() {
        super(HttpStatus.BAD_REQUEST, "존재하지 않는 이메일입니다.");
    }
}