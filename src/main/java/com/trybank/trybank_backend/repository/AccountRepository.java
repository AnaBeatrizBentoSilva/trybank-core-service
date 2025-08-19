package com.trybank.trybank_backend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.trybank.trybank_backend.domain.model.Account;

public interface AccountRepository extends MongoRepository<Account, String>{
    List<Account> findByPersonId(String personId);
}
