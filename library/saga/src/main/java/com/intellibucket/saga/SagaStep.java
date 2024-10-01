package com.intellibucket.saga;

import com.intellibucket.exception.DomainException;

public interface SagaStep<T> {
    void process(T data) throws DomainException;

    void rollback(T data) throws DomainException;
}
