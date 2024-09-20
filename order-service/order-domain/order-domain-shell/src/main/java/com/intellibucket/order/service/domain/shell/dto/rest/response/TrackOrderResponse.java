package com.intellibucket.order.service.domain.shell.dto.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intellibucket.order.service.domain.core.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class TrackOrderResponse {

    @JsonProperty("tracking_id")
    private final UUID trackingId;

    private final OrderStatus status;
}