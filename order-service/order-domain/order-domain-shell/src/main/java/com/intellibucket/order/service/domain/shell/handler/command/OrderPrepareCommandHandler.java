package com.intellibucket.order.service.domain.shell.handler.command;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.identity.order.ord.OrderItemID;
import com.intellibucket.order.service.domain.core.event.StartDeliveryOrderEvent;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.root.OrderItemRoot;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.service.OrderDomainService;
import com.intellibucket.order.service.domain.core.valueobject.OrderItemStatus;
import com.intellibucket.order.service.domain.core.valueobject.OrderStatus;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderPrepareCommand;
import com.intellibucket.order.service.domain.shell.helper.OrderOutboxHelper;
import com.intellibucket.order.service.domain.shell.helper.OrderRepositoryHelper;
import com.intellibucket.order.service.domain.shell.helper.OrderShellHelper;
import com.intellibucket.order.service.domain.shell.mapper.OrderShellDataMapper;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.delivery.OrderStartDeliveryEventPayload;
import com.intellibucket.order.service.domain.shell.security.AbstractSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.intellibucket.saga.order.SagaConstants.ORDER_START_DELIVERY_SAGA_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderPrepareCommandHandler {

    private final OrderDomainService orderDomainService;
    private final OrderRepositoryHelper orderRepositoryHelper;
    private final AbstractSecurityContextHolder securityContextHolder;
    private final OrderShellHelper orderShellHelper;

    @Transactional
    public void handle(OrderPrepareCommand command) throws OrderDomainException {
        OrderID orderId = OrderID.of(command.getOrderId());

        OrderItemID orderItemId = OrderItemID.of(command.getOrderItemId());
        CompanyID companyID = securityContextHolder.currentCompanyID();
        OrderRoot orderRoot = orderRepositoryHelper.findOrderById(orderId);


        if (companyID == null) {
            log.error("Not Authorization to prepare Order with id: {} OrderItem with id: {}", orderId, orderItemId);
            throw new OrderDomainException("Not Authorization to prepare Order with id: " + orderId + " OrderItem with id: " + orderItemId);
        }

        if (!orderRoot.getStatus().isApproved()) {
            throw new OrderDomainException("Order with id: " + orderId + " is not paid, cannot prepare OrderItem with id: " + orderItemId);
        }

        OrderItemRoot orderItemRoot = orderShellHelper.findOrderItemRootInOrderRoot(orderRoot, orderItemId);

        if (!orderItemRoot.getCompanyID().equals(companyID)) {
            log.error("Order with id: {} OrderItem with id: {} authorization not valid: OrderCompanyId: {}, current CompanyId: {}", orderId, orderItemId, companyID, orderItemRoot.getCompanyID());
            throw new OrderDomainException("OrderItem with id: " + orderItemId + " is not in company ID");
        }

        orderItemRoot.prepared();
        log.debug("Order with id: {} OrderItem with id: {} prepared", orderId, orderItemId);
        if (orderRoot.getItems().stream().allMatch(orderItem -> orderItem.getOrderItemStatus().isPrepared())) {
            log.info("Order with id: {} is all items confirmed", orderId);
            orderDomainService.preparedOrder(orderRoot);
        }
        orderRepositoryHelper.saveOrder(orderRoot);
    }
}
