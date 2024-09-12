package com.intellibucket.user.service.domain.core.exception.password;

public class PasswordIsRequiredException extends PasswordDomainException {
    public PasswordIsRequiredException(String message) {
        super(message);
    }
}
