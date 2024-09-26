package com.intellibucket.order.service.domain.shell.port.output.repository;

import com.intellibucket.outbox.OutboxStatus;
import com.intellibucket.message.model.BaseMessageModel;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;

import java.util.List;
import java.util.Optional;

public interface BaseOutboxRepository<T extends BaseMessageModel> {
    Optional<List<T>> findByOutboxStatus(OutboxStatus outboxStatus);

    T save(T outboxMessages) throws OrderDomainException;

    void deleteByOutboxStatus(OutboxStatus outboxStatus);
}
