package com.intellibucket.order.service.domain.shell.dto.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class OrderTrackingQuery {
    @NotNull
    @JsonProperty("tracking_id")
    private final UUID trackingId;
}
