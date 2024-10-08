package com.intellibucket.order.service.domain.shell.dto.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intellibucket.order.service.domain.core.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class OrderResponse {

    private final String id;

    @JsonProperty("tracking_id")
    private final String trackingId;

    private final OrderStatus status;

    private final List<OrderItemResponse> items;

    @JsonProperty("shipping_address")
    private final OrderAddressResponse shippingAddress;

    private final OffsetDateTime createdAt;

}
