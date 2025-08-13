package com.trybank.trybank_backend.domain.dto.person;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PersonUpdateRequest {
    private String fullName;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;
    private String address;
    private String username; 
}
