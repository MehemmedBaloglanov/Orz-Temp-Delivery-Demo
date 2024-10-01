package com.intellibucket.order.service.repository.adapter;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.outbox.model.OutboxMessage;
import com.intellibucket.order.service.domain.shell.port.output.repository.OutboxRepository;
import com.intellibucket.outbox.OutboxStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OutboxRepositoryImpl implements OutboxRepository {
    @Override
    public Optional<List<OutboxMessage>> findByOutboxStatus(OutboxStatus outboxStatus) {
        return Optional.empty();
    }

    @Override
    public OutboxMessage save(OutboxMessage outboxMessages) throws OrderDomainException {
        return null;
    }

    @Override
    public void deleteByOutboxStatus(OutboxStatus outboxStatus) {

    }
}
