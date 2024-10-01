package com.intellibucket.order.service.domain.shell.port.input.listener.concretes;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.dto.message.DeliveryResponse;
import com.intellibucket.order.service.domain.shell.handler.message.OrderDeliveredResponseSagaHandler;
import com.intellibucket.order.service.domain.shell.port.input.listener.abstracts.AbstractDeliveryResponseMessageListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryResponseResponseMessageListener implements AbstractDeliveryResponseMessageListener {
    private final OrderDeliveredResponseSagaHandler orderDeliveredResponseSagaHandler;

    @Override
    public void orderDelivered(DeliveryResponse deliveryResponse) throws OrderDomainException {
        orderDeliveredResponseSagaHandler.process(deliveryResponse);
    }

    @Override
    public void orderCancelled(DeliveryResponse deliveryResponse) throws OrderDomainException {
        orderDeliveredResponseSagaHandler.rollback(deliveryResponse);
    }
}
