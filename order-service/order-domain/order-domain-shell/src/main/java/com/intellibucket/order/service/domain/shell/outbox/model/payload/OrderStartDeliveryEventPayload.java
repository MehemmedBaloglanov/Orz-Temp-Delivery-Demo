package com.intellibucket.order.service.domain.shell.outbox.model.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@SuperBuilder
public class OrderStartDeliveryEventPayload extends BaseEventPayload {

}
