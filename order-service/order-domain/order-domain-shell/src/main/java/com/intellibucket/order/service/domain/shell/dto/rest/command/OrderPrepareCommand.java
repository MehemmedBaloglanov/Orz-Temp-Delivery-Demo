package com.intellibucket.order.service.domain.shell.dto.rest.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPrepareCommand {

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("order_item_id")
    private String orderItemId;

}
