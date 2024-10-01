package com.intellibucket.order.service.domain.shell.outbox.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@SuperBuilder
public class OrderCancelPaymentEventPayload extends BaseEventPayload {

    @JsonProperty
    private String customerId;

    @JsonProperty
    private BigDecimal price;
}
