package com.intellibucket.order.service.domain.shell.outbox.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@SuperBuilder
@AllArgsConstructor
public class OrderStartDeliveryEventPayload extends BaseEventPayload {


    @JsonProperty
    private String customerId;

    @JsonProperty
    private String companyId;

    @JsonProperty
    private OffsetDateTime createdAt;

}
