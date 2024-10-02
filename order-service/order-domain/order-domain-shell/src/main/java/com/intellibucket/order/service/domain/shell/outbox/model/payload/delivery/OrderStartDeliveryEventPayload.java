package com.intellibucket.order.service.domain.shell.outbox.model.payload.delivery;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.BaseEventPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class OrderStartDeliveryEventPayload implements BaseEventPayload {

    @JsonProperty
    private UUID orderId;

    @JsonProperty
    private UUID customerId;

    @JsonProperty
    private OffsetDateTime createdAt;

    @JsonProperty
    private OrderStartDeliveryEventAddress address;
}
