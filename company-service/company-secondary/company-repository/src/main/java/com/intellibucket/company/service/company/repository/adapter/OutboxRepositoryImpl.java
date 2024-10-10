package com.intellibucket.company.service.company.repository.adapter;

import com.intellibucket.company.service.company.repository.entity.OutboxJpaEntity;
import com.intellibucket.company.service.company.repository.entity.OutboxJpaStatus;
import com.intellibucket.company.service.company.repository.helper.CompanyRepositoryDataHelper;
import com.intellibucket.company.service.company.repository.mapper.CompanyDataAccessMapper;
import com.intellibucket.company.service.company.repository.repository.CompanyOutboxRepository;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.outbox.model.OutboxMessage;
import com.intellibucket.company.service.domain.shell.port.output.repository.OutboxRepository;
import com.intellibucket.outbox.OutboxStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OutboxRepositoryImpl implements OutboxRepository {

    private final CompanyDataAccessMapper companyDataAccessMapper;
    private final CompanyOutboxRepository companyJpaRepository;
    private final CompanyRepositoryDataHelper companyRepositoryDataHelper;

    @Override
    public Optional<List<OutboxMessage>> findByOutboxStatus(OutboxStatus outboxStatus) throws CompanyDomainException {
        OutboxJpaStatus outboxJpaStatus = companyRepositoryDataHelper.outboxStatusToOutboxJpaStatus(outboxStatus);
        Optional<List<OutboxJpaEntity>> outboxJpaEntityOptional = companyJpaRepository.findByOutboxStatus(outboxJpaStatus);
        if (outboxJpaEntityOptional.isPresent()) {
            List<OutboxJpaEntity> outboxJpaEntityList = outboxJpaEntityOptional.get();
            List<OutboxMessage> outboxMessages = new ArrayList<>();

            for (OutboxJpaEntity item : outboxJpaEntityList) {
                OutboxMessage outboxMessage = companyDataAccessMapper.mapOutboxJapEntityToOutboxMessage(item);
                outboxMessages.add(outboxMessage);
            }

            return Optional.of(outboxMessages);

        } else {
            return Optional.empty();
        }
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
