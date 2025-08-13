package com.trybank.trybank_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Retorna HTTP 422 quando há uma regra de negócio violada (ex.: email ou username já cadastrado).
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
