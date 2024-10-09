package com.intellibucket.company.service.domain.shell.dto.message.order.refund;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intellibucket.outbox.payload.BaseEventPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCompanyRefundResponse implements BaseEventPayload {
    @JsonProperty
    private UUID orderId;

    @JsonProperty
    private UUID customerId;

    @JsonProperty
    private List<OrderRefundResponseProduct> products;

    @JsonProperty
    private BigDecimal price;

    @JsonProperty
    private OffsetDateTime createdAt;
}
