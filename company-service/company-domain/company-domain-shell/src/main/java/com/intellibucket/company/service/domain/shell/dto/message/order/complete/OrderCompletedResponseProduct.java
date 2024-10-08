package com.intellibucket.company.service.domain.shell.dto.message.order.complete;

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
public class OrderCompletedResponseProduct {
    @JsonProperty
    private UUID productId;

    @JsonProperty
    private UUID companyId;

    @JsonProperty
    private BigDecimal price;

    @JsonProperty
    private BigDecimal subTotal;
}
