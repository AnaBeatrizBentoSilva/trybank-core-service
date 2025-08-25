package com.trybank.trybank_backend.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "operations")
public class Operation {
    @Id
    private String id;

    private TypeOperation typeOperation;
    private String description;
    private LocalDate data;
    private BigDecimal value;
    private String accountSourceId;
    private String accountDestinationId;
}
