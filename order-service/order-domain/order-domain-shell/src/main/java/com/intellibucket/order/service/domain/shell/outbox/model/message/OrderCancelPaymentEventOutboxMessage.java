package com.intellibucket.order.service.domain.shell.outbox.model.message;

import com.intellibucket.message.model.BaseMessageModel;
import com.intellibucket.saga.SagaStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class OrderCancelPaymentEventOutboxMessage extends BaseMessageModel {

    @Setter
    private SagaStatus sagaStatus;

}
