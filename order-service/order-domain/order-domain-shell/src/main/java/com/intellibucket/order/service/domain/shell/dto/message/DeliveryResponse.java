package com.intellibucket.order.service.domain.shell.dto.message;

import com.intellibucket.order.service.domain.core.valueobject.DeliveryStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class DeliveryResponse {
    private String orderId;
    private String customerId;
    private String sagaId;
    private DeliveryStatus status;
    private String failureMessage;
    private Instant createdAt;
}
