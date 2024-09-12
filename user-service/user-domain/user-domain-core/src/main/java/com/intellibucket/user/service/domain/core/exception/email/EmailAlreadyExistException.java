package com.intellibucket.user.service.domain.core.exception.email;

public class EmailAlreadyExistException extends EmailDomainException {
    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
