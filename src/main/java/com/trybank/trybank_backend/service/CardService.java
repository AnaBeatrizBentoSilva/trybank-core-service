package com.trybank.trybank_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trybank.trybank_backend.domain.model.Card;
import com.trybank.trybank_backend.repository.CardRepository;

@Service
public class CardService {
    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> listCardsByAccount(String accountId) {
        return cardRepository.findByAccountId(accountId);
    }

    public void deleteCard(String cardId) {
        cardRepository.deleteById(cardId);
    }
}
