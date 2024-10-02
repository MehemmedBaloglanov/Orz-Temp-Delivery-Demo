package com.intellibucket.order.service.repository.repository;

import com.intellibucket.order.service.repository.model.outbox.OutboxJpaEntity;
import com.intellibucket.order.service.repository.model.outbox.OutboxJpaStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface OrderOutboxRepository extends JpaRepository<OutboxJpaEntity, Long> {
    Optional<List<OutboxJpaEntity>> findByOutboxStatus(OutboxJpaStatus outboxJpaStatus);

    void deleteByOutboxStatus(OutboxJpaStatus outboxJpaStatus);
}
