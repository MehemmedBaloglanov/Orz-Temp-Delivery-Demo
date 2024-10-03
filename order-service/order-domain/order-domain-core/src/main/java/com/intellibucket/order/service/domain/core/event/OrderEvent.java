package com.intellibucket.order.service.domain.core.event;

import com.intellibucket.domain.event.DomainEvent;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;

@Getter
@RequiredArgsConstructor
public class OrderEvent implements DomainEvent<OrderRoot> {
    private final OrderRoot orderRoot;
    private final OffsetDateTime createdAt;
}
