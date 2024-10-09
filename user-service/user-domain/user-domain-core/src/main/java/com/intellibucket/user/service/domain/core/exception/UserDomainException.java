package com.intellibucket.user.service.domain.core.exception;
import com.intellibucket.domain.exception.DomainException;

public class UserDomainException extends DomainException {
    public UserDomainException(String message) {
        super(message);
    }
  public UserDomainException(String message, Throwable cause) {
    super(message, cause);
  }
}