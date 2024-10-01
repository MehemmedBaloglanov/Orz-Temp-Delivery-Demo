package com.intellibucket.order.service.domain.shell.outbox.model.payload.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.BaseEventPayload;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public class OrderCompanyCancelEventPayload implements BaseEventPayload {
    @JsonProperty
    private String orderId;

    @JsonProperty
    private List<OrderCompanyEventProduct> products;

    @JsonProperty
    private BigDecimal price;

    @JsonProperty
    private OffsetDateTime createdAt;
}
