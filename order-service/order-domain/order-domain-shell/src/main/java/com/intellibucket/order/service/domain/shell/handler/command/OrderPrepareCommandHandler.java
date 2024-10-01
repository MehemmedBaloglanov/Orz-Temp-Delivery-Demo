package com.intellibucket.order.service.domain.shell.handler.command;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.identity.order.ord.OrderItemID;
import com.intellibucket.order.service.domain.core.event.StartDeliveryOrderEvent;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.exception.OrderNotFoundException;
import com.intellibucket.order.service.domain.core.root.OrderItemRoot;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.service.OrderDomainService;
import com.intellibucket.order.service.domain.core.valueobject.OrderItemStatus;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderPrepareCommand;
import com.intellibucket.order.service.domain.shell.helper.OrderOutboxHelper;
import com.intellibucket.order.service.domain.shell.helper.OrderRepositoryHelper;
import com.intellibucket.order.service.domain.shell.mapper.OrderShellMapper;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.OrderStartDeliveryEventPayload;
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
public class OrderPrepareCommandHandler {

    private final OrderDomainService orderDomainService;
    private final OrderRepositoryHelper orderRepositoryHelper;
    private final OrderRepository orderRepository;
    private final AbstractSecurityContextHolder securityContextHolder;
    private final OrderShellMapper orderShellMapper;
    private final OrderOutboxHelper orderOutboxHelper;

    @Transactional
    public void handle(OrderPrepareCommand command) throws OrderDomainException {
        OrderID orderId = OrderID.of(command.getOrderId());
        OrderItemID orderItemId = OrderItemID.of(command.getOrderItemId());
        CompanyID companyID = securityContextHolder.currentCompanyID();


        if (companyID == null) {
            log.error("Authorization need to confirm Order with id: {} OrderItem with id: {}", orderId, orderItemId);
            throw new OrderDomainException("Authorization need to confirm Order with id: " + orderId + " OrderItem with id: " + orderItemId);
        }

        Optional<OrderRoot> orderRootOptional = orderRepository.findById(orderId);
        if (orderRootOptional.isEmpty()) {
            log.error("Order with id: {} not found", orderId);
            throw new OrderNotFoundException("Order with id: " + orderId + " not found");
        }

        OrderRoot orderRoot = orderRootOptional.get();

        Optional<OrderItemRoot> orderItemRootOptional = orderRoot.getItems().stream().filter(orderItem -> orderItem.getRootID().equals(orderItemId)).findFirst();

        if (orderItemRootOptional.isEmpty()) {
            log.error("OrderItem with id: {} not found in OrderId: {}", orderItemId, orderId);
            throw new OrderNotFoundException("Order with id: " + orderId + " not found in OrderId: " + orderId);
        }

        OrderItemRoot orderItemRoot = orderItemRootOptional.get();

        if (!orderItemRoot.getCompanyID().equals(companyID)) {
            log.error("Order with id: {} OrderItem with id: {} authorization not valid: OrderCompanyId: {}, current CompanyId: {}", orderId, orderItemId, companyID, orderItemRoot.getCompanyID());
            throw new OrderDomainException("OrderItem with id: " + orderItemId + " is not in company ID");
        }

        orderItemRoot.prepare();
        orderRepositoryHelper.saveOrder(orderRoot);

        if (orderRoot.getItems().stream().noneMatch(orderItem -> orderItem.getOrderItemStatus() == OrderItemStatus.PREPARED)) {
            log.info("Order with id: {} is all items confirmed", orderId);
            StartDeliveryOrderEvent startDeliveryOrderEvent = orderDomainService.preparedOrder(orderRoot);
            OrderStartDeliveryEventPayload orderStartDeliveryEventPayload = orderShellMapper.startDeliveryOrderEventToOrderStartDeliveryEventPayload(startDeliveryOrderEvent);
            orderOutboxHelper.createAndSaveOrderStartDeliveryOutboxMessage(orderStartDeliveryEventPayload);
        }
    }
}
