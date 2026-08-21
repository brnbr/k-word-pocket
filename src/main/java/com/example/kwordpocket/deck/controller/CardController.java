package com.example.kwordpocket.deck.controller;

import com.example.kwordpocket.deck.dto.CardRequest;
import com.example.kwordpocket.deck.dto.CardResponse;
import com.example.kwordpocket.deck.service.CardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping
    public ResponseEntity<CardResponse> createCard(@Valid @RequestBody CardRequest request) {
        Long tempUserId = 1L; // 인증 연동 전 임시 유저 ID
        CardResponse response = cardService.createCard(request, tempUserId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}