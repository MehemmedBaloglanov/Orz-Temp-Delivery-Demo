package com.intellibucket.order.service.domain.shell.handler.command;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.exception.OrderNotFoundException;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.service.OrderDomainService;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderAssignCommand;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderAssignCommandHandler {

    private final OrderRepository orderRepository;
    private final OrderDomainService orderDomainService;

    @Transactional
    public void handle(OrderAssignCommand orderAssignCommand) throws OrderDomainException {

        OrderID orderId = OrderID.of(orderAssignCommand.getOrderId());

        Optional<OrderRoot> orderRoot = orderRepository.findById(orderId);
        if (orderRoot.isEmpty()) {
            throw new OrderNotFoundException("Order not found by id");
        }

        orderDomainService.approveOrder(orderRoot.get());
        orderRepository.save(orderRoot.get());

    }
}
