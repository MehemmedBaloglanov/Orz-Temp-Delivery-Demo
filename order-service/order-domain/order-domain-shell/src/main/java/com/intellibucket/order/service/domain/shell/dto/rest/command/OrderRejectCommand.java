package com.intellibucket.order.service.domain.shell.dto.rest.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@AllArgsConstructor
@ToString
public class OrderRejectCommand {

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("order_item_id")
    private String orderItemId;

    @JsonProperty("reject_message")
    private String rejectMessage;

}
