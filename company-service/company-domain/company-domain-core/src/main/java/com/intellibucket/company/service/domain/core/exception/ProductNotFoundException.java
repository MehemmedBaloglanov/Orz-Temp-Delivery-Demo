package com.intellibucket.company.service.domain.core.exception;


import com.intellibucket.domain.exception.DomainException;

public class ProductNotFoundException extends DomainException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
