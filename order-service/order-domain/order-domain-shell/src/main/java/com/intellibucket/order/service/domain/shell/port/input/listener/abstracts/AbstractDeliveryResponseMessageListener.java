package com.intellibucket.order.service.domain.shell.port.input.listener.abstracts;


import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.dto.message.DeliveryResponse;

public interface AbstractDeliveryResponseMessageListener {
    void orderDelivered(DeliveryResponse deliveryResponse) throws OrderDomainException;

    void orderCancelled(DeliveryResponse deliveryResponse) throws OrderDomainException;
}
