package com.intellibucket.company.service.domain.core.exception;

public class ValidateException extends CompanyDomainException{
    public ValidateException(String message) {
        super(message);
    }

    //todo
    public ValidateException(String message, Throwable cause) {
        super(message, cause);
    }
}
