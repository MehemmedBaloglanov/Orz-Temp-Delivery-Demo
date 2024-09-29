package com.intellibucket.order.service.domain.shell.outbox.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;

@Getter
@SuperBuilder
public abstract class BaseEventPayload {
    @JsonProperty
    private String orderId;

    @JsonProperty
    private OffsetDateTime createdAt;
}
