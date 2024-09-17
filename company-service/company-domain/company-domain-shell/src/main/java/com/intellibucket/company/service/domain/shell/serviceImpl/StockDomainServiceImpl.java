package com.intellibucket.company.service.domain.shell.serviceImpl;

import com.intellibucket.company.service.domain.core.event.stock.StockCreatedEvent;
import com.intellibucket.company.service.domain.core.event.stock.StockDecreasedEvent;
import com.intellibucket.company.service.domain.core.event.stock.StockIncreasedEvent;
import com.intellibucket.company.service.domain.core.event.stock.StockValidationFailedEvent;
import com.intellibucket.company.service.domain.core.exception.StockInsufficientException;
import com.intellibucket.company.service.domain.core.exception.ValidateException;
import com.intellibucket.company.service.domain.core.root.StockRoot;
import com.intellibucket.company.service.domain.core.service.StockDomainService;
import java.time.OffsetDateTime;

public class StockDomainServiceImpl implements StockDomainService {
    @Override
    public StockCreatedEvent createStock(StockRoot stockRoot) throws ValidateException {
        stockRoot.initialize();
        return new StockCreatedEvent(stockRoot, OffsetDateTime.now());
    }

    @Override
    public StockDecreasedEvent decreaseStock(StockRoot stockRoot) throws ValidateException, StockInsufficientException {
        stockRoot.removeStock(stockRoot.getStockQuantity());
        return new StockDecreasedEvent(stockRoot,OffsetDateTime.now());
    }

    @Override
    public StockIncreasedEvent increaseStock(StockRoot stockRoot) throws ValidateException {
        stockRoot.addStock(stockRoot.getStockQuantity());
        return new StockIncreasedEvent(stockRoot,OffsetDateTime.now());
    }

    @Override
    public StockValidationFailedEvent validationFailedStock(StockRoot stockRoot) throws ValidateException {
        stockRoot.validateStockQuantity();
        return new StockValidationFailedEvent(stockRoot,OffsetDateTime.now());
    }
}
