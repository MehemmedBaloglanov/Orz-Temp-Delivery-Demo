package com.intellibucket.order.service.repository.adapter;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.outbox.model.message.OrderCompletedEventOutboxMessage;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderCompleteOutboxRepository;
import com.intellibucket.outbox.OutboxStatus;

import java.util.List;
import java.util.Optional;

public class OrderCompleteOutboxRepositoryImpl implements OrderCompleteOutboxRepository {
    @Override
    public Optional<List<OrderCompletedEventOutboxMessage>> findByOutboxStatus(OutboxStatus outboxStatus) {
        return Optional.empty();
    }

    @Override
    public OrderCompletedEventOutboxMessage save(OrderCompletedEventOutboxMessage outboxMessages) throws OrderDomainException {
        return null;
    }

    @Override
    public void deleteByOutboxStatus(OutboxStatus outboxStatus) {

    }
}
