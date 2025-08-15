package com.trybank.trybank_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.trybank.trybank_backend.domain.dto.auth.AuthRequest;
import com.trybank.trybank_backend.domain.dto.auth.AuthResponse;
import com.trybank.trybank_backend.domain.dto.auth.SignupRequest;
import com.trybank.trybank_backend.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@RequestBody SignupRequest signupRequest) {
        authService.signup(signupRequest);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        return authService.login(authRequest);
    }
}
