package com.intellibucket.order.service.domain.shell.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class PaymentRefundResponse {
    private UUID orderId;
    private String failureMessage;
    private Instant createdAt;
}
