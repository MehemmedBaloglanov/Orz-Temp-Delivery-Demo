package com.intellibucket.company.service.company.repository.entity;

import com.intellibucket.company.service.domain.core.valueobject.CompanyStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "companies")
public class CompanyJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID companyId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompanyStatus status;
    @Column(nullable = false)
    private BigDecimal amount ;

    @Embedded
    private CompanyJpaAddress companyAddress;
}
