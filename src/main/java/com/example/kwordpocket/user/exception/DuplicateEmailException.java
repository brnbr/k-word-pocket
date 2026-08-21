package com.example.kwordpocket.user.exception;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;

public class DuplicateEmailException extends ServiceException {
    public DuplicateEmailException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}