package com.intellibucket.order.service.domain.shell.dto.message;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DeliveryResponse {
    private String orderId;
}
