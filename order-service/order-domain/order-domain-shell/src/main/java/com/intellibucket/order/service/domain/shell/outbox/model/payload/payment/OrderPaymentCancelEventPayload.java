package com.intellibucket.order.service.domain.shell.outbox.model.payload.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.BaseEventPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Builder
@AllArgsConstructor
public class OrderPaymentCancelEventPayload implements BaseEventPayload {

    @JsonProperty
    private String orderId;

    @JsonProperty
    private String customerId;

    @JsonProperty
    private String companyId;

    @JsonProperty
    private BigDecimal price;

    @JsonProperty
    private OffsetDateTime createdAt;
}
