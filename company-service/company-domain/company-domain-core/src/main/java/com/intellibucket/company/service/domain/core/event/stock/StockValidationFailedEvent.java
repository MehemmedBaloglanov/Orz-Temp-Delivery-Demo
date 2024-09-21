package com.intellibucket.company.service.domain.core.event.stock;

import com.intellibucket.company.service.domain.core.root.StockRoot;

import java.time.OffsetDateTime;

public class StockValidationFailedEvent extends StockEvent{
    public StockValidationFailedEvent(StockRoot stockRoot, OffsetDateTime createdTime) {
        super(stockRoot, createdTime);
    }
}
