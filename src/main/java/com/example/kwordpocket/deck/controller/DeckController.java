package com.example.kwordpocket.deck.controller;

import com.example.kwordpocket.deck.dto.DeckRequest;
import com.example.kwordpocket.deck.dto.DeckResponse;
import com.example.kwordpocket.deck.service.DeckService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/decks")
@RequiredArgsConstructor
public class DeckController {

    private final DeckService deckService;

    @PostMapping
    public ResponseEntity<DeckResponse> createDeck(@Valid @RequestBody DeckRequest request) {
        Long tempUserId = 1L; // 로그인 연동 전 임시 ID
        DeckResponse response = deckService.createDeck(request, tempUserId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}