package com.intellibucket.order.service.domain.shell.dto.rest.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderStartDeliveryCommand {
    @JsonProperty("order_id")
    @NotBlank
    private String orderId;
}
