package com.trybank.trybank_backend.domain.dto.person;

import lombok.Data;

@Data
public class PersonUpdateRequest {
    private String username;
    private String email;
    private String phoneNumber;
    private String address;
}
