package com.intellibucket.order.service.domain.shell.dto.rest.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderConfirmCommand {

    @JsonProperty("order_id")
    @NotBlank
    private String orderId;

    @JsonProperty("order_id")
    @NotBlank
    private String orderItemId;
}
