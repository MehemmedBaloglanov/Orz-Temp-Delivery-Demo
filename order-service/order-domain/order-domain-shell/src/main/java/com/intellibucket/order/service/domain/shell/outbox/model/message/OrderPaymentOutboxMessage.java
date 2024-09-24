package com.intellibucket.order.service.domain.shell.outbox.model.message;

import com.food.ordering.system.outbox.OutboxStatus;
import com.food.ordering.system.saga.SagaStatus;
import com.intellibucket.message.model.BaseMessageModel;
import com.intellibucket.order.service.domain.core.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class OrderPaymentOutboxMessage implements BaseMessageModel {
    private UUID id;
    private UUID sagaId;
    private OffsetDateTime createdAt;
    @Setter
    private OffsetDateTime processedAt;
    private String type;
    private String payload;
    @Setter
    private SagaStatus sagaStatus;
    @Setter
    private OrderStatus orderStatus;
    @Setter
    private OutboxStatus outboxStatus;
    private int version;

}
