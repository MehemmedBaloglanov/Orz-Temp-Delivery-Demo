package com.intellibucket.order.service.domain.shell.handler.command;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.order.service.domain.core.event.OrderCancelledEvent;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.exception.OrderNotFoundException;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.service.OrderDomainService;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderCancelCommand;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderRepository;
import com.intellibucket.order.service.domain.shell.security.AbstractSecurityContextHolder;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class OrderCancelCommandHandler{

    private final OrderRepository orderRepository;
    private final OrderDomainService orderDomainService;
    private final AbstractSecurityContextHolder securityContextHolder;

    public void handle(OrderCancelCommand command) throws OrderDomainException {

        OrderID orderId = OrderID.of(command.getOrderId());
        Optional<OrderRoot> orderRoot = orderRepository.findById(orderId);

        if (orderRoot.isEmpty()) {
            throw new OrderNotFoundException("Order not found with id");
        }

        if (!orderRoot.get().getUserId().equals(securityContextHolder.currentUserID())){
            throw new OrderDomainException("User is not authorized to order cancel");
        }

        OrderCancelledEvent orderCancelEvent = orderDomainService.initCancel(orderRoot.get());

        //FIXME Outboxa save et

        orderRepository.save(orderRoot.get());

    }

}
