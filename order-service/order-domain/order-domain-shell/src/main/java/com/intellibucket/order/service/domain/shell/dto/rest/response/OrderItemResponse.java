package com.intellibucket.order.service.domain.shell.dto.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class OrderItemResponse {
    private final UUID productId;
    private final Integer quantity;
    private final BigDecimal price;
    private final BigDecimal subTotal;
}