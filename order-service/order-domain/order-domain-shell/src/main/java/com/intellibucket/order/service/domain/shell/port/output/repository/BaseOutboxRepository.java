package com.intellibucket.order.service.domain.shell.port.output.repository;

import com.intellibucket.message.model.BaseMessageModel;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.outbox.OutboxStatus;

import java.util.List;
import java.util.Optional;

public interface BaseOutboxRepository<T extends BaseMessageModel> {

    /**
     * This method retrieves a list of {@link T} instances from the outbox repository based on the provided outbox status.
     *
     * @param outboxStatus The status of the outbox messages to be retrieved.
     * @return An {@link Optional} containing a list of {@link T} instances with the specified outbox status.
     * If no messages are found, the {@link Optional} will be empty.
     */
    Optional<List<T>> findByOutboxStatus(OutboxStatus outboxStatus);

    T save(T outboxMessages) throws OrderDomainException;

    void deleteByOutboxStatus(OutboxStatus outboxStatus);
}
