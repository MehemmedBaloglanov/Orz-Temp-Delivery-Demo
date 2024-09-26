package com.intellibucket.order.service.domain.shell.outbox.model.message;

import com.intellibucket.outbox.OutboxStatus;
import com.intellibucket.message.model.BaseMessageModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class OrderStartDeliveryEventOutboxMessage implements BaseMessageModel {
    private UUID id;
    private OffsetDateTime createdAt;
    private String type;
    private String payload;

    @Setter
    private OutboxStatus outboxStatus;

    @Setter
    private OffsetDateTime processedAt;

    private int version;
}
