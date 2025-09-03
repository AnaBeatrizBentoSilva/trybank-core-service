package com.trybank.trybank_backend.domain.dto.user;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class UserInfoDTO {
    private String id;
    private String fullName;
    private String username;
    private String email;
    private String phoneNumber;
    private String cpf;
    private String address;

    private String agency;
    private String accountNumber;
    private BigDecimal balance;

    private String cardNumber;
    private String cardHolderName;
    private String securityCode;
    private LocalDate expiryDate;
}
