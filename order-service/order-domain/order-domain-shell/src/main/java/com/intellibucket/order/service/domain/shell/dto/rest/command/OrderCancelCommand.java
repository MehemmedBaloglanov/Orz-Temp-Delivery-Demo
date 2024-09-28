package com.intellibucket.order.service.domain.shell.dto.rest.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderCancelCommand {

    @NotBlank
    @JsonProperty("order_id")
    private final String orderId;
}
