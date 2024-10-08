package com.intellibucket.order.service.domain.shell.dto.message;

import com.intellibucket.order.service.domain.core.valueobject.ApproveStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
public class ApproveResponse {
    private UUID orderId;
    private ApproveStatus status;
    private String failureMessage;
    private Instant createdAt;
}
