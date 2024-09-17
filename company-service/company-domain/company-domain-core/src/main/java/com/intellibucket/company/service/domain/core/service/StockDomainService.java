package com.intellibucket.company.service.domain.core.service;

import com.intellibucket.company.service.domain.core.event.stock.StockCreatedEvent;
import com.intellibucket.company.service.domain.core.event.stock.StockDecreasedEvent;
import com.intellibucket.company.service.domain.core.event.stock.StockIncreasedEvent;
import com.intellibucket.company.service.domain.core.event.stock.StockValidationFailedEvent;
import com.intellibucket.company.service.domain.core.exception.StockInsufficientException;
import com.intellibucket.company.service.domain.core.exception.ValidateException;
import com.intellibucket.company.service.domain.core.root.StockRoot;

public interface StockDomainService {

    StockCreatedEvent createStock(StockRoot stockRoot) throws ValidateException;

    StockDecreasedEvent decreaseStock(StockRoot stockRoot) throws ValidateException, StockInsufficientException;

    StockIncreasedEvent increaseStock(StockRoot stockRoot) throws ValidateException;

    StockValidationFailedEvent validationFailedStock(StockRoot stockRoot) throws ValidateException;

}
