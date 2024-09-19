package com.intellibucket.order.service.domain.shell.dto.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intellibucket.order.service.domain.core.root.OrderItemRoot;
import com.intellibucket.order.service.domain.core.valueobject.OrderAddress;
import com.intellibucket.order.service.domain.core.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class OrderResponse {

    private final String id;

    @JsonProperty("tracking_id")
    private final UUID trackingId;

    private final OrderStatus status;

 private final List<OrderItemRoot> items;

    @JsonProperty("shipping_address")
   private final OrderAddress shippingAddress;

    private final OffsetDateTime createdAt;

}
