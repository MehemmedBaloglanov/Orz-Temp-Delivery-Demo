package com.intellibucket.order.service.domain.shell.dto.rest.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class OrderTrackingQuery {
    @NotNull
    @JsonProperty("tracking_id")
    private final String trackingId;
}
