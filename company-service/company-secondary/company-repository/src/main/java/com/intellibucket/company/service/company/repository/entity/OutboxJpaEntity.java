package com.intellibucket.company.service.company.repository.entity;

import jakarta.persistence.*;
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

    //@Column(columnDefinition = "text")
    private String payload;

    @Enumerated(EnumType.STRING)
    private OutboxJpaStatus outboxStatus;

    private OffsetDateTime createdAt;

    private OffsetDateTime processedAt;

}
