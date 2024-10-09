package com.intellibucket.company.service.company.repository.adapter;

import com.intellibucket.company.service.company.repository.entity.OutboxJpaEntity;
import com.intellibucket.company.service.company.repository.entity.OutboxJpaStatus;
import com.intellibucket.company.service.company.repository.mapper.CompanyDataAccessMapper;
import com.intellibucket.company.service.company.repository.repository.CompanyOutboxRepository;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.outbox.model.OutboxMessage;
import com.intellibucket.company.service.domain.shell.port.output.repository.OutboxRepository;
import com.intellibucket.outbox.OutboxStatus;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class OutboxRepositoryImpl implements OutboxRepository {

    private final CompanyDataAccessMapper companyDataAccessMapper;
    private final CompanyOutboxRepository companyJpaRepository;


    @Override
    public Optional<List<OutboxMessage>> findByOutboxStatus(OutboxStatus outboxStatus) {
        return Optional.empty();
    }


    @Override
    public OutboxMessage save(OutboxMessage outboxMessage) {
        OutboxJpaEntity outboxJpaEntity = companyDataAccessMapper.mapOutboxMessageToOutboxJpaEntity(outboxMessage);
        OutboxJpaEntity save = companyJpaRepository.save(outboxJpaEntity);
        return companyDataAccessMapper.mapOutboxJapEntityToOutboxMessage(save);
    }

    @Override
    public void deleteByOutboxStatus(OutboxStatus outboxStatus) throws CompanyDomainException {
        companyJpaRepository.deleteByOutboxStatus(companyDataAccessMapper.mapOutboxStatusToOutboxJpaStatus(outboxStatus));
    }
}
