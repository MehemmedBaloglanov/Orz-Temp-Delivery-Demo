package com.intellibucket.company.service.domain.shell.outbox.model.payload;

import com.intellibucket.order.service.domain.core.valueobject.ApproveStatus;
import com.intellibucket.outbox.payload.BaseEventPayload;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductApprovePayload implements BaseEventPayload {
    private UUID orderId;
    private ApproveStatus status;
    private String failureMessage;
    private Instant createdAt;
}
