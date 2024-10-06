package com.intellibucket.company.service.domain.core.exception;


import com.intellibucket.domain.exception.DomainException;

public class CompanyDomainException extends DomainException {

    public CompanyDomainException(String message) {
        super(message);
    }

    public CompanyDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
