package com.intellibucket.order.service.repository.adapter;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.port.output.repository.BaseOutboxRepository;
import com.intellibucket.outbox.OutboxStatus;

import java.util.List;
import java.util.Optional;

public class OrderStartDeliveryOutboxRepositoryImpl implements BaseOutboxRepository<OrderStartDeliveryEventOutboxMessage> {
    @Override
    public Optional<List<OrderStartDeliveryEventOutboxMessage>> findByOutboxStatus(OutboxStatus outboxStatus) {
        return Optional.empty();
    }

    @Override
    public OrderStartDeliveryEventOutboxMessage save(OrderStartDeliveryEventOutboxMessage outboxMessages) throws OrderDomainException {
        return null;
    }

    @Override
    public void deleteByOutboxStatus(OutboxStatus outboxStatus) {

    }
}
