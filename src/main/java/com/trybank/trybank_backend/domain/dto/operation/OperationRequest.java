package com.trybank.trybank_backend.domain.dto.operation;

import java.math.BigDecimal;

import com.trybank.trybank_backend.domain.model.TypeOperation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationRequest {
    private TypeOperation typeOperation;
    private String description;
    private BigDecimal value;
    private String accountSourceId;

    private String beneficiaryAgency;
    private String beneficiaryAccountNumber;
}
