package com.intellibucket.order.service.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jdk.jfr.MemoryAddress;
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
    private UUID orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    private UUID productID;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal subTotal;

    private OffsetDateTime createdAt;

}
