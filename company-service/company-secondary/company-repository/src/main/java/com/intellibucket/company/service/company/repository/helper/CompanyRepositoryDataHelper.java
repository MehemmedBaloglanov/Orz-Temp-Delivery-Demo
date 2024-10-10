package com.intellibucket.company.service.company.repository.helper;

import com.intellibucket.company.service.company.repository.entity.OutboxJpaStatus;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.outbox.OutboxStatus;
import org.springframework.stereotype.Component;

import static com.intellibucket.company.service.company.repository.entity.OutboxJpaStatus.STARTED;

@Component
public class CompanyRepositoryDataHelper {

    public OutboxJpaStatus outboxStatusToOutboxJpaStatus(OutboxStatus outboxStatus) throws CompanyDomainException {
        return switch (outboxStatus) {
            case STARTED -> STARTED;
            case COMPLETED -> OutboxJpaStatus.COMPLETED;
            case FAILED -> OutboxJpaStatus.FAILED;
            default -> throw new CompanyDomainException("Unsupported OutboxStatus: " + outboxStatus);
        };
    }
}
