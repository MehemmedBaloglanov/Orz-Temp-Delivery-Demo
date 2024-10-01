package com.intellibucket.order.service.domain.shell.handler.command;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.identity.order.ord.OrderItemID;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.root.OrderItemRoot;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.service.OrderDomainService;
import com.intellibucket.order.service.domain.core.valueobject.OrderItemStatus;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderConfirmCommand;
import com.intellibucket.order.service.domain.shell.helper.OrderRepositoryHelper;
import com.intellibucket.order.service.domain.shell.helper.OrderShellHelper;
import com.intellibucket.order.service.domain.shell.security.AbstractSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderConfirmCommandHandler {

    private final AbstractSecurityContextHolder securityContextHolder;
    private final OrderRepositoryHelper orderRepositoryHelper;
    private final OrderDomainService orderDomainService;
    private final OrderShellHelper orderShellHelper;

    @Transactional
    public void handle(OrderConfirmCommand orderConfirmCommand) throws OrderDomainException {

        OrderID orderId = OrderID.of(orderConfirmCommand.getOrderId());
        OrderItemID orderItemId = OrderItemID.of(orderConfirmCommand.getOrderItemId());
        CompanyID companyID = securityContextHolder.currentCompanyID();


        if (companyID == null) {
            log.error("Authorization need to confirm Order with id: {} OrderItem with id: {}", orderId, orderItemId);
            throw new OrderDomainException("Authorization need to confirm Order with id: " + orderId + " OrderItem with id: " + orderItemId);
        }

        OrderRoot orderRoot = orderRepositoryHelper.findOrderById(orderId);

        OrderItemRoot orderItemRoot = orderShellHelper.findOrderItemRootInOrderRoot(orderRoot, orderItemId);

        if (!orderItemRoot.getCompanyID().equals(companyID)) {
            log.error("Order with id: {} OrderItem with id: {} authorization not valid: OrderCompanyId: {}, current CompanyId: {}", orderId, orderItemId, companyID, orderItemRoot.getCompanyID());
            throw new OrderDomainException("OrderItem with id: " + orderItemId + " is not in company ID");
        }

        orderItemRoot.confirm();

        if (orderRoot.getItems().stream().noneMatch(item -> item.getOrderItemStatus() != OrderItemStatus.DRAFT)) {
            orderDomainService.confirmOrder(orderRoot);
            log.info("Order with id: {} is all items confirmed", orderId);
        }

        orderRepositoryHelper.saveOrder(orderRoot);

    }


}
