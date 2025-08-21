package com.trybank.trybank_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trybank.trybank_backend.domain.model.Card;
import com.trybank.trybank_backend.service.CardService;

@RestController
@RequestMapping("/cards")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/account/{accountId}")
    public List<Card> getCardsByAccount(@PathVariable String accountId) {
        return cardService.listCardsByAccount(accountId);
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable String id) {
        cardService.deleteCard(id);
    }

}
