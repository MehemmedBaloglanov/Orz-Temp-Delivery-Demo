package com.intellibucket.company.service.company.repository.entity;

import com.intellibucket.company.service.domain.core.valueobject.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name = "products")
    public class ProductEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private String id;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false)
        private String description;

        @Column(nullable = false)
        private String price;

        @Column(nullable = false)
        private Integer quantity;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private ProductStatus status;

        @ManyToOne
        @JoinColumn(name = "company_id", nullable = false)
        private CompanyEntity company;

}
