package com.intellibucket.order.service.domain.shell.port.output.repository;

import com.food.ordering.system.outbox.OutboxStatus;
import com.food.ordering.system.saga.SagaStatus;
import com.intellibucket.order.service.domain.shell.outbox.model.message.OrderPaymentOutboxMessage;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderPaymentOutboxRepository {
    Optional<List<OrderPaymentOutboxMessage>> findByTypeAndOutboxStatusAndSagaStatus(String orderSagaName, OutboxStatus outboxStatus, SagaStatus[] sagaStatus);

    Optional<OrderPaymentOutboxMessage> findByTypeAndSagaIdAndSagaStatus(String orderSagaName, UUID sagaId, SagaStatus[] sagaStatus);

    OrderPaymentOutboxMessage save(OrderPaymentOutboxMessage orderPaymentOutboxMessage);

    void deleteByTypeAndOutboxStatusAndSagaStatus(String orderSagaName, OutboxStatus outboxStatus, SagaStatus[] sagaStatus);
}
