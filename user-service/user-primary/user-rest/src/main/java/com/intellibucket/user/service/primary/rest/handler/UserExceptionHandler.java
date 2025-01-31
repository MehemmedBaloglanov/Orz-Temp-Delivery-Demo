package com.intellibucket.user.service.primary.rest.handler;

import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.email.EmailAlreadyExistException;
import com.intellibucket.user.service.domain.core.exception.password.PasswordValidationException;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.exception.user.UserSavedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
@Slf4j
@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(UserSavedException.class)
    public ResponseEntity<String> handleUserSavedException(UserSavedException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>("User could not be saved: " + ex.getMessage(), HttpStatus.BAD_REQUEST); // 400
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<String> handleEmailValidationException(EmailAlreadyExistException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>("Duplicate e-mail! " + ex.getMessage(), HttpStatus.CONFLICT); // 409
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>("User not found: " + ex.getMessage(), HttpStatus.NOT_FOUND); // 404
    }

    @ExceptionHandler(PasswordValidationException.class)
    public ResponseEntity<String> handlePasswordValidationException(PasswordValidationException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>("Try again!: " + ex.getMessage(), HttpStatus.BAD_REQUEST); // 400
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>("Invalid input: " + ex.getMessage(), HttpStatus.BAD_REQUEST); // 400
    }

    @ExceptionHandler(UserDomainException.class)
    public ResponseEntity<String> userDomainException(UserDomainException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>("User domain error: " + ex.getMessage(), HttpStatus.BAD_REQUEST); // 400
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
    }
}