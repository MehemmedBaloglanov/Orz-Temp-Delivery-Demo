package com.intellibucket.order.service.domain.shell.handler.message;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.order.service.domain.core.event.OrderCompletedEvent;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.exception.OrderNotFoundException;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.service.OrderDomainService;
import com.intellibucket.order.service.domain.shell.dto.message.DeliveryResponse;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderDeliveredMessageHandler {

    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;

    public void handle(DeliveryResponse deliveryResponse) throws OrderDomainException {
        OrderID orderID = OrderID.of(deliveryResponse.getOrderId());
        Optional<OrderRoot> orderRootOptional = orderRepository.findById(orderID);
        if (orderRootOptional.isEmpty()) {
            log.error("Order with id: {} not found", orderID);
            throw new OrderNotFoundException("Order with id: " + orderID + " not found");
        }
        OrderRoot orderRoot = orderRootOptional.get();
        OrderCompletedEvent orderCompletedEvent = orderDomainService.orderComplete(orderRoot);
        orderRepository.save(orderCompletedEvent.getOrderRoot());


        // Fixme company odenisini et send event OrderCompletedEvent
    }
}
