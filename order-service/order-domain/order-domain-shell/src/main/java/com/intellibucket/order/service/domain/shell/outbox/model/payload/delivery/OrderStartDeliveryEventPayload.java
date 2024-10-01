package com.intellibucket.order.service.domain.shell.outbox.model.payload.delivery;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.BaseEventPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Builder
@AllArgsConstructor
public class OrderStartDeliveryEventPayload implements BaseEventPayload {

    @JsonProperty
    private String orderId;

    @JsonProperty
    private String customerId;

    @JsonProperty
    private BigDecimal price;

    @JsonProperty
    private ZonedDateTime createdAt;
}
