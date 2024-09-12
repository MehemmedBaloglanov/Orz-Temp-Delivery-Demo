package com.intellibucket.company.service.domain.core.event.stock;

import com.intellibucket.company.service.domain.core.root.StockRoot;

import java.time.OffsetDateTime;

public class StockCreatedEvent extends StockEvent{
    public StockCreatedEvent(StockRoot stockRoot, OffsetDateTime createdTime) {
        super(stockRoot, createdTime);
    }
}
