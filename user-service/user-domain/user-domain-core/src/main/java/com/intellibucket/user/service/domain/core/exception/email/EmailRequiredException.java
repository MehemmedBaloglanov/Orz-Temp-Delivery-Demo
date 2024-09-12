package com.intellibucket.user.service.domain.core.exception.email;

public class EmailRequiredException extends EmailDomainException {
    public EmailRequiredException(String message) {
        super(message);
    }
}