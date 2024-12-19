package com.intellibucket.company.service.company.repository.repository;

import com.intellibucket.company.service.company.repository.entity.OutboxJpaEntity;
import com.intellibucket.company.service.company.repository.entity.OutboxJpaStatus;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyOutboxRepository extends JpaRepository<OutboxJpaEntity, Long> {
    Optional<List<OutboxJpaEntity>> findByOutboxStatus(OutboxJpaStatus status) throws CompanyDomainException;

    void deleteByOutboxStatus(OutboxJpaStatus status);
}
