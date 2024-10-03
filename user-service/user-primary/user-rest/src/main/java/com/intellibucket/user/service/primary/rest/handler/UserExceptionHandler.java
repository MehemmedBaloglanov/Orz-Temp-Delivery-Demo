//package com.intellibucket.user.service.primary.rest.handler;
//
//import com.intellibucket.user.service.domain.core.exception.email.EmailAlreadyExistException;
//import com.intellibucket.user.service.domain.core.exception.password.PasswordValidationException;
//import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
//import com.intellibucket.user.service.domain.core.exception.user.UserSavedException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.orm.ObjectOptimisticLockingFailureException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//
//@RestControllerAdvice
//public class UserExceptionHandler {
//    @ExceptionHandler(UserSavedException.class)
//    public ResponseEntity<String> handleUserSavedException(UserSavedException ex, WebRequest request) {
//        return new ResponseEntity<>("User could not be saved: " + ex.getMessage(), HttpStatus.BAD_REQUEST); // 400
//    }
//
//    @ExceptionHandler(EmailAlreadyExistException.class)
//    public ResponseEntity<String> handleEmailValidationException(EmailAlreadyExistException ex, WebRequest request) {
//        return new ResponseEntity<>("User already exist with this email: " + ex.getMessage(), HttpStatus.CONFLICT); // 409
//    }
//
//    @ExceptionHandler(UserNotFoundException.class)
//    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
//        return new ResponseEntity<>("User not found: " + ex.getMessage(), HttpStatus.NOT_FOUND); // 404
//    }
//
//    @ExceptionHandler(PasswordValidationException.class)
//    public ResponseEntity<String> handlePasswordValidationException(PasswordValidationException ex, WebRequest request) {
//        return new ResponseEntity<>("Old password is invalid!: " + ex.getMessage(), HttpStatus.BAD_REQUEST); // 400
//    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
//        return new ResponseEntity<>("Invalid input: " + ex.getMessage(), HttpStatus.BAD_REQUEST); // 400
//    }
//
//    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
//    public ResponseEntity<String> handleOptimisticLockingFailure(ObjectOptimisticLockingFailureException ex, WebRequest request) {
//        return new ResponseEntity<>("Conflict: The user has been modified by another transaction. " + ex.getMessage(), HttpStatus.CONFLICT); // 409
//    }
//
//    @ExceptionHandler(IllegalStateException.class)
//    public ResponseEntity<String> handleIllegalStateException(IllegalStateException ex, WebRequest request) {
//        return new ResponseEntity<>("Operation not allowed: " + ex.getMessage(), HttpStatus.CONFLICT); // 409
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGenericException(Exception ex, WebRequest request) {
//        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // 500
//    }
//}