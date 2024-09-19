package com.intellibucket.order.service.repository.model;

import com.intellibucket.order.service.domain.core.valueobject.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    private UUID id;

    private BigDecimal price;

    private OrderStatus orderStatus;

    @Embedded
    private OrderJpaAddress address;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> items;

    private String orderNumber;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    @Version
    private int version;

    private UUID trackingID;


}
