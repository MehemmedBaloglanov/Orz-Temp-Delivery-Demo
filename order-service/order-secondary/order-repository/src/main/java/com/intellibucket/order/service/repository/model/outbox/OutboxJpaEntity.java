package com.intellibucket.order.service.repository.model.outbox;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "_outbox")
public class OutboxJpaEntity {
    @Id
    private UUID id;

    private String sagaName;

    private String payload;

    private OutboxJpaStatus outboxStatus;

    private OffsetDateTime createdAt;

    private OffsetDateTime processedAt;

}
