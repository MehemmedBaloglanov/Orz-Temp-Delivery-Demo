package com.intellibucket.company.service.company.repository.entity;

import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intellibucket.company.service.domain.core.valueobject.ProductStatus;
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
@Table(name = "products")
public class ProductJpaEntity {

    @Id
    private UUID productId;

//    @Column(nullable = false)
//    private String name;
//
//    @Column(nullable = false)
//    private String description;

    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantity;

    private Integer stock;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus status;

    private UUID companyId;

}
