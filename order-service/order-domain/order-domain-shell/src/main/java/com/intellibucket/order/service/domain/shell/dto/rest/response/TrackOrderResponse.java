package com.intellibucket.order.service.domain.shell.dto.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intellibucket.order.service.domain.core.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class TrackOrderResponse {

    @JsonProperty("order_id")
    private final UUID orderId;

    private final OrderStatus status;
}