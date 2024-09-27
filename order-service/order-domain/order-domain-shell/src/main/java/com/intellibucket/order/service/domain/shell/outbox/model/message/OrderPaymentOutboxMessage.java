package com.intellibucket.order.service.domain.shell.outbox.model.message;

import com.intellibucket.message.model.BaseMessageModel;
import com.intellibucket.order.service.domain.core.valueobject.OrderStatus;
import com.intellibucket.outbox.OutboxStatus;
import com.intellibucket.saga.SagaStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@SuperBuilder
public class OrderPaymentOutboxMessage extends BaseMessageModel {

    @Setter
    private SagaStatus sagaStatus;

    @Setter
    private OrderStatus orderStatus;

}
