package com.intellibucket.order.service.domain.shell.outbox.model.payload.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.BaseEventPayload;
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
public class OrderCompanyApproveEventPayload implements BaseEventPayload {

    @JsonProperty
    private UUID orderId;

    @JsonProperty
    private List<OrderCompanyApproveEventProduct> products;

    @JsonProperty
    private OffsetDateTime createdAt;

}
