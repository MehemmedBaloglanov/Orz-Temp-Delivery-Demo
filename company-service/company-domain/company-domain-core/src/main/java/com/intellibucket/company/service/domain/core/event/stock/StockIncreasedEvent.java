package com.intellibucket.company.service.domain.core.event.stock;

import com.intellibucket.company.service.domain.core.root.StockRoot;

import java.time.OffsetDateTime;

public class StockIncreasedEvent extends StockEvent{
    public StockIncreasedEvent(StockRoot stockRoot, OffsetDateTime createdTime) {
        super(stockRoot, createdTime);
    }
}
