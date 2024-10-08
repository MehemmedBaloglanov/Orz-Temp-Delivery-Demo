package com.intellibucket.company.service.domain.shell.dto.message.order.complete;

import com.intellibucket.order.service.domain.shell.outbox.model.payload.BaseEventPayload;
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
public class OrderCompletedResponse implements BaseEventPayload {
    private UUID customerId;
    private UUID orderId;
    private List<OrderCompletedResponseProduct> products;
    private OffsetDateTime createdAt;
}
