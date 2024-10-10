package com.intellibucket.order.service.domain.shell.outbox.model.payload.completed;

import com.intellibucket.outbox.payload.BaseEventPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCompletedEventPayload implements BaseEventPayload {
    private UUID customerId;
    private UUID orderId;
    private List<OrderCompletedEventProduct> products;
    private OffsetDateTime createdAt;
}
