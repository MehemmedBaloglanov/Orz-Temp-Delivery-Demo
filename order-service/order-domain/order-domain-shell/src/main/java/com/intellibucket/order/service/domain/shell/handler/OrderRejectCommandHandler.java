package com.intellibucket.order.service.domain.shell.handler;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.service.OrderDomainService;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderRejectCommand;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderRepositoryAdapter;
import com.intellibucket.order.service.domain.shell.security.AbstractSecurityContextHolder;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class OrderRejectCommandHandler {

    private final OrderRepositoryAdapter orderRepositoryAdapter;
    private final OrderDomainService orderDomainService;
    private final AbstractSecurityContextHolder securityContextHolder;

    public void handle(OrderRejectCommand command) throws OrderDomainException {

        OrderID orderId = OrderID.of(command.getOrderId());
        Optional<OrderRoot> orderRoot = orderRepositoryAdapter.findById(orderId);

        if (orderRoot.isEmpty()) {
            throw new OrderDomainException("Order not found with id");
        }

        if (!orderRoot.get().getUserId().equals(securityContextHolder.currentUserID())){
            throw new OrderDomainException("User is not authorized to order reject");
        }
        //FIXME OrderDomainServicede Reject Order methodu yaratmaq
//        orderDomainService.
    }

}
