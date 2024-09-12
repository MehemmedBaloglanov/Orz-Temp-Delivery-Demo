package com.intellibucket.company.service.domain.core.service;

import com.intellibucket.company.service.domain.core.event.stock.StockCreatedEvent;
import com.intellibucket.company.service.domain.core.event.stock.StockDecreasedEvent;
import com.intellibucket.company.service.domain.core.event.stock.StockIncreasedEvent;
import com.intellibucket.company.service.domain.core.event.stock.StockValidationFailedEvent;
import com.intellibucket.company.service.domain.core.root.StockRoot;

public interface StockDomainService {

    StockCreatedEvent createStock(StockRoot stockRoot);

    StockDecreasedEvent decreaseStock(StockRoot stockRoot);

    StockIncreasedEvent increaseStock(StockRoot stockRoot);

    StockValidationFailedEvent validationFailedStock(StockRoot stockRoot);

}
