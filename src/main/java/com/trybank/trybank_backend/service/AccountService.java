package com.trybank.trybank_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.trybank.trybank_backend.domain.model.Account;
import com.trybank.trybank_backend.repository.AccountRepository;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccountsByPersonId(String personId) {
        return accountRepository.findByPersonId(personId);
    }

    public void deleteAccount(String accountId) {
        accountRepository.deleteById(accountId);
    }
}
