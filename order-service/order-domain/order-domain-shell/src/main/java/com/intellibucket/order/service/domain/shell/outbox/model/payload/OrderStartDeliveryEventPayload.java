package com.intellibucket.order.service.domain.shell.outbox.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Builder
@AllArgsConstructor
public class OrderStartDeliveryEventPayload {

    @JsonProperty
    private String orderId;

    @JsonProperty
    private String customerId;

    @JsonProperty
    private String companyId;

    @JsonProperty
    private OffsetDateTime createdAt;

}
