package com.intellibucket.order.service.domain.shell.dto.rest.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;



import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class OrderTrackingQuery {
    @NotNull
    @JsonProperty("tracking_id")
    private final UUID trackingId;
}
