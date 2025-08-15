package com.trybank.trybank_backend.domain.dto.auth;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
