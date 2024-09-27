package com.intellibucket.order.service.domain.shell.port.output.repository;

import com.intellibucket.outbox.OutboxStatus;
import com.intellibucket.saga.SagaStatus;
import com.intellibucket.order.service.domain.shell.outbox.model.message.OrderPaymentOutboxMessage;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderPaymentOutboxRepository extends BaseOutboxRepository<OrderPaymentOutboxMessage> {
    Optional<List<OrderPaymentOutboxMessage>> findByTypeAndOutboxStatusAndSagaStatus(String orderSagaName, OutboxStatus outboxStatus, SagaStatus[] sagaStatus);

    Optional<OrderPaymentOutboxMessage> findByTypeAndSagaIdAndSagaStatus(String orderSagaName, UUID sagaId, SagaStatus[] sagaStatus);

    void deleteByTypeAndOutboxStatusAndSagaStatus(String orderSagaName, OutboxStatus outboxStatus, SagaStatus[] sagaStatus);
}
