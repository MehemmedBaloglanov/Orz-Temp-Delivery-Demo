package com.intellibucket.order.service.domain.shell.outbox.model;

import com.intellibucket.message.model.BaseMessageModel;
import com.intellibucket.outbox.OutboxStatus;
import com.intellibucket.saga.SagaStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Builder
public class OutboxMessage implements BaseMessageModel {
    private UUID id;
    private UUID sagaId;
    private String sagaName;

    private String payload;

    @Setter
    private OutboxStatus outboxStatus;

    private OffsetDateTime createdAt;

    @Setter
    private SagaStatus sagaStatus;

    @Setter
    private OffsetDateTime processedAt;
}
