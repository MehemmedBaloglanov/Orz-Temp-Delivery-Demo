package com.intellibucket.company.service.domain.core.event.stock;

import com.intellibucket.company.service.domain.core.root.StockRoot;
import com.intellibucket.domain.event.DomainEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;

@RequiredArgsConstructor
@Getter
public class StockEvent implements DomainEvent<StockEvent> {
    private final StockRoot stockRoot;
    private final OffsetDateTime createdTime;
}
