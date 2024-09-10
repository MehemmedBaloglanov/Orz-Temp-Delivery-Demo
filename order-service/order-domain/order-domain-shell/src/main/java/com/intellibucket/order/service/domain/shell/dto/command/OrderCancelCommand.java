package com.intellibucket.order.service.domain.shell.dto.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderCancelCommand {
    @JsonProperty("order_id")
    private final String orderId;
}
