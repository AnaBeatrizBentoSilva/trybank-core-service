package com.trybank.trybank_backend.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.trybank.trybank_backend.domain.dto.operation.OperationRequest;
import com.trybank.trybank_backend.domain.dto.operation.OperationResponse;
import com.trybank.trybank_backend.domain.model.Account;
import com.trybank.trybank_backend.domain.model.Operation;
import com.trybank.trybank_backend.domain.model.TypeOperation;
import com.trybank.trybank_backend.exception.BusinessException;
import com.trybank.trybank_backend.repository.AccountRepository;
import com.trybank.trybank_backend.repository.OperationRepository;

@Service
public class OperationService {
    private final OperationRepository operationRepository;
    private final AccountRepository accountRepository;

    public OperationService(OperationRepository operationRepository, AccountRepository accountRepository) {
        this.operationRepository = operationRepository;
        this.accountRepository = accountRepository;
    }

    public OperationResponse createOperation(OperationRequest operationRequest) {
        Account accountSource = accountRepository.findById(operationRequest.getAccountSourceId())
                .orElseThrow(() -> new BusinessException("Conta de origem não encontrada"));

        Account accountDestination = null;
        if (operationRequest.getTypeOperation() == TypeOperation.TRANSFERENCIA) {
            accountDestination = accountRepository.findByAgencyAndAccountNumber(
                operationRequest.getBeneficiaryAgency(),
                operationRequest.getBeneficiaryAccountNumber())
                .orElseThrow(() -> new BusinessException("Conta de destino não encontrada"));
        }

        int currentHour = LocalDateTime.now().getHour();
        BigDecimal amount = operationRequest.getValue();
        BigDecimal maxLimit = (currentHour >= 22 || currentHour < 6) ? new BigDecimal(1000)
                : new BigDecimal(10000);

        if (amount.compareTo(maxLimit) > 0) {
            throw new BusinessException("Valor excede o limite permitido para esse horário.");
        }

        // Atualiza saldo das contas
        switch (operationRequest.getTypeOperation()) {
            case DEPOSITO:
                accountSource.setBalance(accountSource.getBalance().add(operationRequest.getValue()));
                break;
            case SAQUE:
                if (amount.compareTo(new BigDecimal("10")) < 0){
                    throw new BusinessException("O valor mínimo para saque é R$10,00");
                }
                if (amount.remainder(new BigDecimal("10")).compareTo(BigDecimal.ZERO) != 0) {
                    throw new BusinessException("O valor do saque deve ser múltiplo de 10.");
                }
                if (accountSource.getBalance().compareTo(amount) < 0) {
                    throw new BusinessException("Saldo insuficiente");
                }
                accountSource.setBalance(accountSource.getBalance().subtract(amount));
                break;
            case TRANSFERENCIA:
                if (accountSource.getBalance().compareTo(amount) < 0) {
                    throw new BusinessException("Saldo insuficiente");
                }
                accountSource.setBalance(accountSource.getBalance().subtract(amount));
                accountDestination.setBalance(accountDestination.getBalance().add(amount));
                accountRepository.save(accountDestination);
                break;
            case INVESTIMENTO:
                // lógica de investimento futura
                break;
            case CAMBIO:
                // lógica de câmbio futura
                break;
        }

        accountRepository.save(accountSource);

        // Cria a operação
        Operation operation = Operation.builder()
                .typeOperation(operationRequest.getTypeOperation())
                .description(operationRequest.getDescription())
                .data(LocalDate.now())
                .value(operationRequest.getValue())
                .accountSourceId(accountSource.getId())
                .accountDestinationId(accountDestination != null ? accountDestination.getId() : null)
                .build();

        Operation savedOperation = operationRepository.save(operation);

        return mapToResponse(savedOperation);
    }

    public List<OperationResponse> getOperationsByAccountSourceId(String accountSourceId) {
        return operationRepository.findByAccountSourceId(accountSourceId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private OperationResponse mapToResponse(Operation operation) {
        return OperationResponse.builder()
                .id(operation.getId())
                .typeOperation(operation.getTypeOperation())
                .description(operation.getDescription())
                .data(operation.getData())
                .value(operation.getValue())
                .accountSourceId(operation.getAccountSourceId())
                .accountDestinationId(operation.getAccountDestinationId())
                .build();
    }

}
