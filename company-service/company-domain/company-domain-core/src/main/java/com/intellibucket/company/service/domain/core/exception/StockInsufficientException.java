package com.intellibucket.company.service.domain.core.exception;

import com.intellibucket.exception.DomainException;

public class StockInsufficientException extends DomainException {

    public StockInsufficientException(String message) {
        super(message);
    }

    public StockInsufficientException(String message, Throwable cause) {
        super(message, cause);
    }
}
