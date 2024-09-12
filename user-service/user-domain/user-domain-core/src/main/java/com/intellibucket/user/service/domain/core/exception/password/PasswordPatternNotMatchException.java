package com.intellibucket.user.service.domain.core.exception.password;

public class PasswordPatternNotMatchException extends PasswordDomainException {
    public PasswordPatternNotMatchException(String message) {
        super(message);
    }
}
