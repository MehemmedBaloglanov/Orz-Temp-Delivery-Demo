package com.intellibucket.user.service.domain.core.exception.phonenumber;

import com.intellibucket.user.service.domain.core.exception.UserDomainException;

public class PhoneNumberIsRequiredException extends PhoneNumberDomainException {
    public PhoneNumberIsRequiredException(String message) {
        super(message);
    }
}
