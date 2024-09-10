package com.intellibucket.order.service.domain.shell.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intellibucket.order.service.domain.core.valueobject.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class TrackOrderResponse {

    @JsonProperty("tracking_id")
    private final UUID trackingId;

    private final OrderStatus status;
}