package com.intellibucket.order.service.repository.model.order;

import jakarta.persistence.*;
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
@Table(name = "order_items")
@EqualsAndHashCode(of = "id")
public class OrderItemEntity {
    @Id
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    private UUID productId;

    private UUID companyId;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal subTotal;

    private OrderItemJpaStatus status;

}
