package com.intellibucket.user.service.domain.core.exception.user;

import com.intellibucket.user.service.domain.core.exception.UserDomainException;

public class UserNotFoundException extends UserDomainException {
    public UserNotFoundException(String message) {
        super(message);
    }

}
