package com.intellibucket.order.service.repository.model.order;

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
@Table(name = "orders")
@EqualsAndHashCode(of = "id")
@ToString
public class OrderEntity {

    @Id
    private UUID id;

    private UUID customerId;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private OrderJpaStatus orderStatus;

    @Embedded
    private OrderJpaAddress address;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> items;

    private String orderNumber;

    private OffsetDateTime createdAt;

    private String failureMessage;

    @Enumerated(EnumType.STRING)
    private OrderCancellationJpaType cancellationType;

//    @Version
//    private int version;


}
