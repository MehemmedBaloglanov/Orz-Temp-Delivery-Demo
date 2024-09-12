package com.intellibucket.user.service.domain.core.exception.phonenumber;

import com.intellibucket.user.service.domain.core.exception.UserDomainException;

public class PhoneNumberAlreadyExistException extends PhoneNumberDomainException {
    public PhoneNumberAlreadyExistException(String message) {
        super(message);
    }
}
