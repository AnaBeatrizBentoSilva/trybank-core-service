package com.trybank.trybank_backend.domain.dto.person;

import java.time.LocalDate;

import com.trybank.trybank_backend.domain.model.Person;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponse {
    private String id;
    private String fullName;
    private LocalDate dateOfBirth;
    private String cpf;
    private String email;
    private String phoneNumber;
    private String address;
    private String username;

    public static PersonResponse fromEntity(Person person) {
        return PersonResponse.builder()
                .id(person.getId())
                .fullName(person.getFullName())
                .dateOfBirth(person.getDateOfBirth())
                .cpf(person.getCpf())
                .email(person.getEmail())
                .phoneNumber(person.getPhoneNumber())
                .address(person.getAddress())
                .username(person.getUsername())
                .build();
    }
}
