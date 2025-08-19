package com.trybank.trybank_backend.service;

import java.math.BigDecimal;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.trybank.trybank_backend.domain.dto.auth.AuthRequest;
import com.trybank.trybank_backend.domain.dto.auth.AuthResponse;
import com.trybank.trybank_backend.domain.dto.auth.SignupRequest;
import com.trybank.trybank_backend.domain.model.Account;
import com.trybank.trybank_backend.domain.model.Person;
import com.trybank.trybank_backend.exception.BusinessException;
import com.trybank.trybank_backend.repository.AccountRepository;
import com.trybank.trybank_backend.repository.PersonRepository;
import com.trybank.trybank_backend.security.JwtUtil;
import com.trybank.trybank_backend.util.AccountNumberGenerator;

@Service
public class AuthService {
    private final PersonRepository personRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthService(PersonRepository personRepository, AccountRepository accountRepository, PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.personRepository = personRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public void signup(SignupRequest request) {
        if (personRepository.existsByCpf(request.getCpf())) {
            throw new BusinessException("CPF already exists");
        }

        if (personRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email already exists");
        }

        if (personRepository.existsByUsername(request.getUsername())) {
            throw new BusinessException("Username already exists");
        }

        Person person = new Person();

        person.setFullName(request.getFullName());
        person.setDateOfBirth(request.getDateOfBirth());
        person.setCpf(request.getCpf());
        person.setEmail(request.getEmail());
        person.setPhoneNumber(request.getPhoneNumber());
        person.setAddress(request.getAddress());
        person.setUsername(request.getUsername());
        person.setPassword(passwordEncoder.encode(request.getPassword()));

        personRepository.save(person);

        Account account = Account.builder()
                .agency("0001")
                .accountNumber(AccountNumberGenerator.generateAccountNumber()) 
                .balance(BigDecimal.ZERO) 
                .personId(person.getId())
                .build();

        accountRepository.save(account);
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String token = jwtUtil.generateToken(request.getUsername());
        return new AuthResponse(token);
    }
}
