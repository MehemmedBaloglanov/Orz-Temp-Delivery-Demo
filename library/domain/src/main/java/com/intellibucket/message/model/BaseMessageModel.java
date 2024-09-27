package com.intellibucket.message.model;

import com.intellibucket.outbox.OutboxStatus;

import java.util.UUID;

public interface BaseMessageModel {
    UUID getId();
    String getPayload();
    void setOutboxStatus(OutboxStatus outboxStatus);
}
