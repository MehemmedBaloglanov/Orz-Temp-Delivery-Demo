package com.intellibucket.user.service.domain.core.exception.password;

import com.intellibucket.user.service.domain.core.exception.UserDomainException;

public class PasswordDomainException extends UserDomainException {
    public PasswordDomainException(String message) {
        super(message);
    }
}
