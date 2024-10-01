package com.intellibucket.order.service.repository.adapter;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.outbox.OutboxStatus;
import com.intellibucket.saga.SagaStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OrderPaymentOutboxRepositoryImpl implements OrderPaymentOutboxRepository {
    @Override
    public Optional<List<OrderPaymentOutboxMessage>> findByTypeAndOutboxStatusAndSagaStatus(String orderSagaName, OutboxStatus outboxStatus, SagaStatus[] sagaStatus) {
        return Optional.empty();
    }

    @Override
    public Optional<OrderPaymentOutboxMessage> findByTypeAndSagaIdAndSagaStatus(String orderSagaName, UUID sagaId, SagaStatus[] sagaStatus) {
        return Optional.empty();
    }

    @Override
    public void deleteByTypeAndOutboxStatusAndSagaStatus(String orderSagaName, OutboxStatus outboxStatus, SagaStatus[] sagaStatus) {

    }

    @Override
    public Optional<List<OrderPaymentOutboxMessage>> findByOutboxStatus(OutboxStatus outboxStatus) {
        return Optional.empty();
    }

    @Override
    public OrderPaymentOutboxMessage save(OrderPaymentOutboxMessage outboxMessages) throws OrderDomainException {
        return null;
    }

    @Override
    public void deleteByOutboxStatus(OutboxStatus outboxStatus) {

    }
}
