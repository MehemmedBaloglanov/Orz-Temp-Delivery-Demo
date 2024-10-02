package com.intellibucket.order.service.repository.model.order;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Builder
@EqualsAndHashCode
public class OrderJpaAddress {

    private String street;

    private String city;

    private String state;

}
