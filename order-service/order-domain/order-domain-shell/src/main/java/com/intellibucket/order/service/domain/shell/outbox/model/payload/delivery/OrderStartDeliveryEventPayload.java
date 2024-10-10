package com.intellibucket.order.service.domain.shell.outbox.model.payload.delivery;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intellibucket.outbox.payload.BaseEventPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
