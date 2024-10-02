package com.intellibucket.order.service.domain.shell.outbox.model.payload.delivery;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intellibucket.order.service.domain.core.valueobject.DeliveryStatus;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.BaseEventPayload;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.company.OrderCompletedEventProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class OrderDeliveryCompletedEventPayload implements BaseEventPayload {
    @JsonProperty
    private UUID orderId;

    @JsonProperty
    private UUID customerId;

    @JsonProperty
    private DeliveryStatus status;

    @JsonProperty
    private ZonedDateTime createdAt;

    @JsonProperty
    private List<OrderCompletedEventProduct> products;



}
