package com.example.kwordpocket.faq.service;

public class FaqNotFoundException extends RuntimeException {
    public FaqNotFoundException(String message) {
        super(message);
    }
}