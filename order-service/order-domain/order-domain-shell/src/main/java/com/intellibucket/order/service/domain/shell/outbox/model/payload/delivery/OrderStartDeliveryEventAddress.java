package com.intellibucket.order.service.domain.shell.outbox.model.payload.delivery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class OrderStartDeliveryEventAddress {
    private String street;
    private String city;
    private String state;

}
