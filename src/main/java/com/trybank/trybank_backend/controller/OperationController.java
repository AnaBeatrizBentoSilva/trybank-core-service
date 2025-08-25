package com.trybank.trybank_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trybank.trybank_backend.domain.dto.operation.OperationRequest;
import com.trybank.trybank_backend.domain.dto.operation.OperationResponse;
import com.trybank.trybank_backend.service.OperationService;

@RestController
@RequestMapping("/operations")
public class OperationController {
    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @PostMapping
    public OperationResponse createOperation(@RequestBody OperationRequest operationRequest) {
        return operationService.createOperation(operationRequest);
    }

    @GetMapping("/account/{accountSourceId}")
    public List<OperationResponse> getOperationsByAccountSourceId(@PathVariable String accountSourceId) {
        return operationService.getOperationsByAccountSourceId(accountSourceId);
    }
}
