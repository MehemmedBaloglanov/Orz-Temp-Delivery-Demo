package com.intellibucket.order.service.domain.shell.outbox.model.payload.delivery;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intellibucket.order.service.domain.core.valueobject.DeliveryStatus;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.BaseEventPayload;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
public class OrderDeliveryCompletedEventPayload implements BaseEventPayload {
    @JsonProperty
    private String orderId;

    @JsonProperty
    private String customerId;

    @JsonProperty
    private DeliveryStatus status;

}
