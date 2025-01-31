package com.intellibucket.order.service.domain.shell.dto.message;

import com.intellibucket.order.service.domain.core.valueobject.DeliveryStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
public class DeliveryResponse {
    private UUID orderId;
    private UUID customerId;
    private DeliveryStatus status;
    private String failureMessage;
    private Instant createdAt;
}
