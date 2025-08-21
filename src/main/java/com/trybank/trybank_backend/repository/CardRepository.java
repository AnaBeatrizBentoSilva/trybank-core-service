package com.trybank.trybank_backend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.trybank.trybank_backend.domain.model.Card;

public interface CardRepository extends MongoRepository<Card, String>{
    List<Card> findByAccountId(String accountId);
}
