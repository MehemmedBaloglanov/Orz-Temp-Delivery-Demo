package com.intellibucket.user.service.domain.core.exception.email;

public class DuplicateEmailException extends EmailDomainException {
    public DuplicateEmailException(String message) {
        super(message);
    }
}
