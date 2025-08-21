package com.trybank.trybank_backend.domain.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "cards")
public class Card {
    @Id
    private String id;

    private String cardNumber;
    private String cardHolderName;
    private String securityCode;
    private LocalDate expiryDate;

    @DBRef
    private Account account;
}
