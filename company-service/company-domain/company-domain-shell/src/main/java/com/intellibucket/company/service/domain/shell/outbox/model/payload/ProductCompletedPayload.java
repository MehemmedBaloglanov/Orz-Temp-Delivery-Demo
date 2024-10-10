package com.intellibucket.company.service.domain.shell.outbox.model.payload;

import com.intellibucket.outbox.payload.BaseEventPayload;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCompletedPayload implements BaseEventPayload {
    private UUID orderId;
//    private CompletedStatus status; todo
    private String failureMessage;
    private Instant createdAt;
}
