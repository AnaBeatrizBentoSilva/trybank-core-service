package com.trybank.trybank_backend.domain.dto.operation;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.trybank.trybank_backend.domain.model.TypeOperation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationResponse {
    private String id;
    private TypeOperation typeOperation;
    private String description;
    private LocalDate data;
    private BigDecimal value;
    private String accountSourceId;
    private String accountDestinationId;
}
