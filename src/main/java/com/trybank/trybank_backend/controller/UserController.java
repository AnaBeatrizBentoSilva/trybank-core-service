package com.trybank.trybank_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trybank.trybank_backend.domain.dto.user.UserInfoDTO;
import com.trybank.trybank_backend.domain.model.Account;
import com.trybank.trybank_backend.domain.model.Card;
import com.trybank.trybank_backend.domain.model.Person;
import com.trybank.trybank_backend.repository.AccountRepository;
import com.trybank.trybank_backend.repository.CardRepository;
import com.trybank.trybank_backend.repository.PersonRepository;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final PersonRepository personRepository;
    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;

    @GetMapping("/info")
    public ResponseEntity<UserInfoDTO> getUserInfo(@RequestParam String username){
        Person person = personRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        List<Account> accounts = accountRepository.findByPersonId(person.getId());
        if (accounts.isEmpty()) {
            throw new RuntimeException("Conta não encontrada");
        }
        Account account = accounts.get(0);

        List<Card> cards = cardRepository.findByAccountId(account.getId());
        if (cards.isEmpty()) {
            throw new RuntimeException("Cartão não encontrado");
        }
        Card card = cards.get(0);
        
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setId(person.getId());
        userInfoDTO.setFullName(person.getFullName());
        userInfoDTO.setUsername(person.getUsername());
        userInfoDTO.setEmail(person.getEmail());
        userInfoDTO.setPhoneNumber(person.getPhoneNumber());
        userInfoDTO.setCpf(person.getCpf());
        userInfoDTO.setAddress(person.getAddress());
        userInfoDTO.setAgency(account.getAgency());
        userInfoDTO.setAccountNumber(account.getAccountNumber());
        userInfoDTO.setBalance(account.getBalance());
        userInfoDTO.setCardNumber(card.getCardNumber());
        userInfoDTO.setCardHolderName(card.getCardHolderName());
        userInfoDTO.setSecurityCode(card.getSecurityCode());
        userInfoDTO.setExpiryDate(card.getExpiryDate());

        return ResponseEntity.ok(userInfoDTO);
    }
    
}
