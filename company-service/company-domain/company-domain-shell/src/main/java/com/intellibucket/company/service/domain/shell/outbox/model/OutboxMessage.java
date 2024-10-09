package com.intellibucket.company.service.domain.shell.outbox.model;

import com.intellibucket.domain.message.model.BaseMessageModel;
import com.intellibucket.outbox.OutboxStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Builder
public class OutboxMessage implements BaseMessageModel {
    private UUID id;
    private String sagaName;

    private String payload;

    @Setter
    private OutboxStatus outboxStatus;

    private OffsetDateTime createdAt;

    @Setter
    private OffsetDateTime processedAt;
}
