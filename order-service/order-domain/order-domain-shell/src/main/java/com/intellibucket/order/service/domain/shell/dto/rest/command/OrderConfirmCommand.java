package com.intellibucket.order.service.domain.shell.dto.rest.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderConfirmCommand {

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("order_id")
    private String orderItemId;
}
