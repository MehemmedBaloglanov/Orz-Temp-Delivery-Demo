package com.intellibucket.user.service.domain.core.exception.user;

import com.intellibucket.user.service.domain.core.exception.UserDomainException;

public class UserValidationException extends UserDomainException {
    public UserValidationException(String message) {
        super(message);
    }
}
