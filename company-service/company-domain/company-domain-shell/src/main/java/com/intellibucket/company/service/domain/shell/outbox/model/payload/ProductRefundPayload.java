package com.intellibucket.company.service.domain.shell.outbox.model.payload;

import com.intellibucket.order.service.domain.core.valueobject.ApproveStatus;
import com.intellibucket.outbox.payload.BaseEventPayload;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRefundPayload implements BaseEventPayload {
    private UUID customerId;
    private BigDecimal refundAmount;
    private String failureMessage;
    private Instant createdAt;

    //todo bunun usage yoxdu silinmelidirmi?
}
