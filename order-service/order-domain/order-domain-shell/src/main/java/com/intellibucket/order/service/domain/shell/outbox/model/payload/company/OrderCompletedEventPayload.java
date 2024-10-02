package com.intellibucket.order.service.domain.shell.outbox.model.payload.company;

import com.intellibucket.order.service.domain.shell.outbox.model.payload.BaseEventPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class OrderCompletedEventPayload implements BaseEventPayload {
    private UUID customerId;
    private UUID orderId;
    private List<OrderCompletedEventProduct> products;
    private OffsetDateTime createdAt;
}
