package com.intellibucket.order.service.domain.shell.handler.command;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.order.service.domain.core.event.OrderCancelledEvent;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.service.OrderDomainService;
import com.intellibucket.order.service.domain.core.valueobject.OrderCancelType;
import com.intellibucket.order.service.domain.core.valueobject.OrderStatus;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderRejectCommand;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderRepository;
import com.intellibucket.order.service.domain.shell.security.AbstractSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderRejectCommandHandler {

    private final OrderRepository orderRepository;
    private final OrderDomainService orderDomainService;
    private final AbstractSecurityContextHolder securityContextHolder;

    @Transactional
    public void handle(OrderRejectCommand command) throws OrderDomainException {
        // FIXME duzgun islemir. Ya Company butun ordere Aid Olmalidir yada bir bir orderItem icerisinde gezib
        // FIXME OrderItemi legv etmek ve bunun neticesinde item meblegini musteriye qaytaarmaq lazimdir
        OrderID orderId = OrderID.of(command.getOrderId());
        Optional<OrderRoot> orderRootOptional = orderRepository.findById(orderId);

        if (orderRootOptional.isEmpty()) {
            throw new OrderDomainException("Order not found with id");
        }
        OrderRoot orderRoot = orderRootOptional.get();
        if (!orderRoot.getUserId().equals(securityContextHolder.currentCompanyID())) {
            throw new OrderDomainException("User is not authorized to order reject");
        }
        OrderCancelledEvent orderCancelledEvent = orderDomainService.orderPaymentCancel(orderRoot, OrderCancelType.COMPANY, List.of("order cancelled by company "));

    }

}
