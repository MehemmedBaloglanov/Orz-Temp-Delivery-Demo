package com.intellibucket.order.service.repository.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Builder
public class OrderJpaAddress {

    private String street;

    private String city;

    private String state;

}
