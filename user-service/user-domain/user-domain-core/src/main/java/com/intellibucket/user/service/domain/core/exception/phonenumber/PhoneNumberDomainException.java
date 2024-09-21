package com.intellibucket.user.service.domain.core.exception.phonenumber;

import com.intellibucket.user.service.domain.core.exception.UserDomainException;

public class PhoneNumberDomainException extends UserDomainException {
    public PhoneNumberDomainException(String message) {
        super(message);
    }
}
