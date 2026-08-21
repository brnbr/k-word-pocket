package com.example.kwordpocket.deck.service;

import com.example.kwordpocket.deck.dto.CardRequest;
import com.example.kwordpocket.deck.dto.CardResponse;
import com.example.kwordpocket.deck.entity.Card;
import com.example.kwordpocket.deck.entity.Deck;
import com.example.kwordpocket.deck.repository.CardRepository;
import com.example.kwordpocket.deck.repository.DeckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CardService {

    private final CardRepository cardRepository;
    private final DeckRepository deckRepository;

    @Transactional
    public CardResponse createCard(CardRequest request, Long loginUserId) {
        // 1. 단어장(Deck) 조회
        Deck deck = deckRepository.findById(request.getDeckId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 단어장입니다. id=" + request.getDeckId()));

//        // 2. 단어장 소유자 검증 (Deck에 User가 연결된 경우)
//        if (deck.getUser() != null && !deck.getUser().getId().equals(loginUserId)) {
//            throw new IllegalArgumentException("해당 단어장에 카드를 추가할 권한이 없습니다.");
//        }

        // 3. Entity 변환 및 DB 저장
        Card card = Card.builder()
                .front(request.getFront())
                .back(request.getBack())
                .deck(deck)
                .build();

        return new CardResponse(cardRepository.save(card));
    }
}