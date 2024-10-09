package com.intellibucket.company.service.domain.shell.dto.message.order.refund;

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
public class OrderRefundResponseProduct {
    @JsonProperty
    private UUID productId;

    @JsonProperty
    private UUID companyId;

    @JsonProperty
    private BigDecimal price;

    @JsonProperty
    private BigDecimal subTotal;
}