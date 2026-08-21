package com.example.kwordpocket.deck.service;

import com.example.kwordpocket.deck.dto.DeckRequest;
import com.example.kwordpocket.deck.dto.DeckResponse;
import com.example.kwordpocket.deck.entity.Deck;
import com.example.kwordpocket.deck.repository.DeckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeckService {

    private final DeckRepository deckRepository;

    @Transactional
    public DeckResponse createDeck(DeckRequest request, Long loginUserId) {
        Deck deck = Deck.builder()
                .name(request.getName())
                .build();

        Deck savedDeck = deckRepository.save(deck);
        return new DeckResponse(savedDeck);
    }
}