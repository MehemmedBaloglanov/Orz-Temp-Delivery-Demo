package com.intellibucket.order.service.domain.shell.outbox.model.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

@Getter
@Builder
@AllArgsConstructor
public class OrderPaymentEventPayload {

    @JsonProperty
    private String orderId;
    @JsonProperty
    private String customerId;
    @JsonProperty
    private BigDecimal price;
    @JsonProperty
    private OffsetDateTime createdAt;
    @JsonProperty
    private String paymentOrderStatus;
}
