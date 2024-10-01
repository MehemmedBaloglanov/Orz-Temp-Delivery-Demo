package com.intellibucket.order.service.domain.shell.handler.command;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.order.service.domain.core.event.OrderCancelledEvent;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.exception.OrderNotFoundException;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.service.OrderDomainService;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderCancelCommand;
import com.intellibucket.order.service.domain.shell.helper.OrderOutboxHelper;
import com.intellibucket.order.service.domain.shell.helper.OrderRepositoryHelper;
import com.intellibucket.order.service.domain.shell.mapper.OrderShellMapper;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.OrderCancelPaymentEventPayload;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.OrderPaymentEventPayload;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderRepository;
import com.intellibucket.order.service.domain.shell.security.AbstractSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCancelCommandHandler {

    private final OrderRepository orderRepository;
    private final OrderDomainService orderDomainService;
    private final AbstractSecurityContextHolder securityContextHolder;
    private final OrderRepositoryHelper orderRepositoryHelper;
    private final OrderShellMapper orderShellMapper;
    private final OrderOutboxHelper orderOutboxHelper;


    @Transactional
    public void handle(OrderCancelCommand command) throws OrderDomainException {

        UserID currentUserID = securityContextHolder.currentUserID();

        OrderID orderId = OrderID.of(command.getOrderId());
        Optional<OrderRoot> orderRootOptional = orderRepository.findById(orderId);

        if (orderRootOptional.isEmpty()) {
            log.error("Order not found with id: {}", orderId);
            throw new OrderNotFoundException("Order not found with id: " + orderId);
        }

        OrderRoot orderRoot = orderRootOptional.get();
        if (!orderRoot.getUserId().equals(currentUserID)) {
            log.error("User: {} is not authorized to order: {} cancel", currentUserID, orderId);
            throw new OrderDomainException("User: " + currentUserID + " is not authorized to order: " + orderId + " cancel");
        }

        orderRoot.cancelByCustomer();
        OrderCancelledEvent orderCancelEvent = orderDomainService.orderPaymentCancel(orderRoot, "Customer cancelled order");
        orderRepositoryHelper.saveOrder(orderRoot);

        OrderCancelPaymentEventPayload orderCancelEventPayload = orderShellMapper.orderCancelledEventToOrderPaymentCancelEventPayload(orderCancelEvent);
        orderOutboxHelper.createAndSavePaymentCancelOutboxMessage(orderCancelEventPayload);

    }
}
