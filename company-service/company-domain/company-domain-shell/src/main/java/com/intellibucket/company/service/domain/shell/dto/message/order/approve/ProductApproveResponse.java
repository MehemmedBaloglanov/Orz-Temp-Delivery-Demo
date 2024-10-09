package com.intellibucket.company.service.domain.shell.dto.message.order.approve;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intellibucket.outbox.payload.BaseEventPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductApproveResponse implements BaseEventPayload {

    @JsonProperty
    private UUID orderId;

    @JsonProperty
    private List<ProductApproveResponseProduct> products;

    @JsonProperty
    private OffsetDateTime createdAt;

}
