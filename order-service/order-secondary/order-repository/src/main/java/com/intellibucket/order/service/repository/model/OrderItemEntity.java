package com.intellibucket.order.service.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEntity {
    @Id
    private UUID orderId;

    private UUID productID;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal subTotal;

    private OffsetDateTime createdAt;

}
