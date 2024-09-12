package com.intellibucket.user.service.domain.core.exception.email;

import com.intellibucket.user.service.domain.core.exception.UserDomainException;

public class EmailDomainException extends UserDomainException {
  public EmailDomainException(String message) {
    super(message);
  }
}
