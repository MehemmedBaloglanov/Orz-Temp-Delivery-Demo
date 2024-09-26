package com.intellibucket.order.service.domain.shell.port.input.listener.concretes;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.dto.message.DeliveryResponse;
import com.intellibucket.order.service.domain.shell.handler.message.OrderDeliveredMessageHandler;
import com.intellibucket.order.service.domain.shell.port.input.listener.abstracts.AbstractDeliveryMessageListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryResponseMessageListener implements AbstractDeliveryMessageListener {
    private final OrderDeliveredMessageHandler orderDeliveredMessageHandler;

    @Override
    public void orderDelivered(DeliveryResponse deliveryResponse) throws OrderDomainException {
        orderDeliveredMessageHandler.handle(deliveryResponse);
    }
}
