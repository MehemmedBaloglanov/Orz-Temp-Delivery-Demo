package com.intellibucket.company.service.domain.shell.serviceImpl;

import com.intellibucket.company.service.domain.core.event.stock.StockCreatedEvent;
import com.intellibucket.company.service.domain.core.event.stock.StockDecreasedEvent;
import com.intellibucket.company.service.domain.core.event.stock.StockIncreasedEvent;
import com.intellibucket.company.service.domain.core.event.stock.StockValidationFailedEvent;
import com.intellibucket.company.service.domain.core.root.StockRoot;
import com.intellibucket.company.service.domain.core.service.StockDomainService;

public class StockDomainServiceImpl implements StockDomainService {
    @Override
    public StockCreatedEvent createStock(StockRoot stockRoot) {
        return null;
    }

    @Override
    public StockDecreasedEvent decreaseStock(StockRoot stockRoot) {
        return null;
    }

    @Override
    public StockIncreasedEvent increaseStock(StockRoot stockRoot) {
        return null;
    }

    @Override
    public StockValidationFailedEvent validationFailedStock(StockRoot stockRoot) {
        return null;
    }
}
