package com.trybank.trybank_backend.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.trybank.trybank_backend.domain.dto.auth.AuthRequest;
import com.trybank.trybank_backend.domain.dto.auth.AuthResponse;
import com.trybank.trybank_backend.domain.dto.auth.SignupRequest;
import com.trybank.trybank_backend.domain.model.Person;
import com.trybank.trybank_backend.exception.BusinessException;
import com.trybank.trybank_backend.repository.PersonRepository;
import com.trybank.trybank_backend.security.JwtUtil;


@Service
public class AuthService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthService(PersonRepository personRepository, PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.personRepository = personRepository;
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
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String token = jwtUtil.generateToken(request.getUsername());
        return new AuthResponse(token);
    }
}
