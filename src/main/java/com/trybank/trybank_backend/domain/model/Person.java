package com.trybank.trybank_backend.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("people")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {
    @Id
    private String id;

    private String fullName;

    private java.time.LocalDate dateOfBirth;

    @Indexed(unique = true)
    private String cpf;

    @Indexed(unique = true)
    private String email;

    private String phoneNumber;
    
    private String address;

    @Indexed(unique = true)
    private String username;

    private String password;
}
