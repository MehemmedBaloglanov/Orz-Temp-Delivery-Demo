package com.intellibucket.message.model;

import com.intellibucket.outbox.OutboxStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@SuperBuilder
@AllArgsConstructor
public abstract class BaseMessageModel {
    private UUID id;
    private UUID sagaId;
    private String payload;

    @Setter
    private OutboxStatus outboxStatus;

    private OffsetDateTime createdAt;

    @Setter
    private OffsetDateTime processedAt;

    private int version;
}
