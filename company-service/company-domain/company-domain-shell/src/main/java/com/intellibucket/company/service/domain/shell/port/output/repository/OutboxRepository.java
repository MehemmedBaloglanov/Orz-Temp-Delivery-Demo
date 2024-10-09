package com.intellibucket.company.service.domain.shell.port.output.repository;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.outbox.model.OutboxMessage;
import com.intellibucket.outbox.OutboxStatus;

import java.util.List;
import java.util.Optional;

public interface OutboxRepository {
    OutboxMessage save(OutboxMessage outboxMessage);

    Optional<List<OutboxMessage>> findByOutboxStatus(OutboxStatus outboxStatus);

    void deleteByOutboxStatus(OutboxStatus outboxStatus) throws CompanyDomainException;
}
