package com.example.kwordpocket.global.exception;

public record ErrorResponse(
        String code,
        String message
) {
}
