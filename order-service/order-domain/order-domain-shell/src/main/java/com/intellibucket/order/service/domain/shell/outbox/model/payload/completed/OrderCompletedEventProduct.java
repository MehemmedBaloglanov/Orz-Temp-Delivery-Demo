package com.intellibucket.order.service.domain.shell.outbox.model.payload.completed;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCompletedEventProduct {
    @JsonProperty
    private UUID productId;

    @JsonProperty
    private UUID companyId;

    @JsonProperty
    private BigDecimal price;

    @JsonProperty
    private BigDecimal subTotal;
}
