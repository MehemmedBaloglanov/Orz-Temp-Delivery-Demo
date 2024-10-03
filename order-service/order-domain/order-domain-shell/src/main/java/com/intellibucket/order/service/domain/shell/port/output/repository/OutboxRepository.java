package com.intellibucket.order.service.domain.shell.port.output.repository;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.outbox.model.OutboxMessage;
import com.intellibucket.outbox.OutboxStatus;

import java.util.List;
import java.util.Optional;

public interface OutboxRepository {

    Optional<List<OutboxMessage>> findByOutboxStatus(OutboxStatus outboxStatus) throws OrderDomainException;

    OutboxMessage save(OutboxMessage outboxMessages) throws OrderDomainException;

    void deleteByOutboxStatus(OutboxStatus outboxStatus) throws OrderDomainException;
}
