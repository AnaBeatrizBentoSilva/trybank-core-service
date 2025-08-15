package com.trybank.trybank_backend.domain.dto.auth;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SignupRequest {
    private String fullName;
    private LocalDate dateOfBirth;
    private String cpf;
    private String email;
    private String phoneNumber;
    private String address;
    private String username;
    private String password;
}
