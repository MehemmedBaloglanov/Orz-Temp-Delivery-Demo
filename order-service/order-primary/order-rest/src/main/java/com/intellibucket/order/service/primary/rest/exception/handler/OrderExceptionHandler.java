package com.intellibucket.order.service.primary.rest.exception.handler;

import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class OrderExceptionHandler {

//    // Handle generic exceptions
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGenericException(Exception ex) {
//        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    // Handle entity not found (e.g., Order not found)
//    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
//        return new ResponseEntity<>("Order not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
//    }
//
//    // Handle IllegalArgumentException (e.g., invalid input or status change)
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
//        return new ResponseEntity<>("Invalid input: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }
//
//    // Handle optimistic locking failure exceptions
//    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
//    public ResponseEntity<String> handleOptimisticLockingFailure(ObjectOptimisticLockingFailureException ex) {
//        return new ResponseEntity<>("Conflict: The order has been modified by another transaction. " + ex.getMessage(), HttpStatus.CONFLICT);
//    }
//
//    // Handle IllegalStateException (e.g., business rule violation)
//    @ExceptionHandler(IllegalStateException.class)
//    public ResponseEntity<String> handleIllegalStateException(IllegalStateException ex) {
//        return new ResponseEntity<>("Operation not allowed: " + ex.getMessage(), HttpStatus.CONFLICT);
//    }
//
//    @ExceptionHandler(OrderNotFoundException.class)
//    public ResponseEntity<String> handleOrderNotFoundException(OrderNotFoundException ex) {
//        return new ResponseEntity<>("Operation not allowed: " + ex.getMessage(), HttpStatus.CONFLICT);
//    }
//
//    @ExceptionHandler(OrderDomainException.class)
//    public ResponseEntity<String> handleOrderDomainException(OrderDomainException ex) {
//        return new ResponseEntity<>("Operation not allowed: " + ex.getMessage(), HttpStatus.CONFLICT);
//    }


}
