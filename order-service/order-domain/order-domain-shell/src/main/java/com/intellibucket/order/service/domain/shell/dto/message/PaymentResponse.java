package com.intellibucket.order.service.domain.shell.dto.message;

import com.intellibucket.order.service.domain.core.valueobject.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class PaymentResponse {

    private UUID orderId;
    private UUID paymentId;
    private BigDecimal price;
    private Instant createdAt;
    private PaymentStatus status;
    private String failureMessage;

}
