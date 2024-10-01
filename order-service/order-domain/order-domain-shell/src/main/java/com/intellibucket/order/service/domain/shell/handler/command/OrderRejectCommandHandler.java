package com.intellibucket.order.service.domain.shell.handler.command;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.identity.order.ord.OrderItemID;
import com.intellibucket.order.service.domain.core.event.OrderCancelledEvent;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.root.OrderItemRoot;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.service.OrderDomainService;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderRejectCommand;
import com.intellibucket.order.service.domain.shell.helper.OrderOutboxHelper;
import com.intellibucket.order.service.domain.shell.helper.OrderRepositoryHelper;
import com.intellibucket.order.service.domain.shell.helper.OrderShellHelper;
import com.intellibucket.order.service.domain.shell.mapper.OrderShellMapper;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.company.OrderCompanyEventPayload;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.payment.OrderPaymentCancelEventPayload;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderRepository;
import com.intellibucket.order.service.domain.shell.security.AbstractSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.intellibucket.saga.order.SagaConstants.ORDER_COMPANY_CANCEL_SAGA_NAME;
import static com.intellibucket.saga.order.SagaConstants.ORDER_PAYMENT_CANCEL_SAGA_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderRejectCommandHandler {

    private final OrderRepository orderRepository;
    private final OrderDomainService orderDomainService;
    private final OrderRepositoryHelper orderRepositoryHelper;
    private final AbstractSecurityContextHolder securityContextHolder;
    private final OrderShellMapper orderShellMapper;
    private final OrderOutboxHelper orderOutboxHelper;
    private final OrderShellHelper orderShellHelper;

    @Transactional
    public void handle(OrderRejectCommand command) throws OrderDomainException {

        OrderID orderId = OrderID.of(command.getOrderId());
        OrderItemID orderItemId = OrderItemID.of(command.getOrderItemId());
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

        orderItemRoot.reject();

        OrderCancelledEvent orderCancelledEvent = orderDomainService.orderCompanyCancel(orderRoot, orderItemRoot, command.getRejectMessage());
        orderRepositoryHelper.saveOrder(orderCancelledEvent.getOrderRoot());

        OrderPaymentCancelEventPayload orderCancelEventPayload = orderShellMapper.orderCancelledEventToOrderPaymentCancelEventPayload(orderCancelledEvent);
        orderOutboxHelper.createAndSaveOutboxMessage(orderCancelEventPayload, orderId, ORDER_PAYMENT_CANCEL_SAGA_NAME);

        OrderCompanyEventPayload orderCompanyEventPayload = orderShellMapper.orderCancelledEventToOrderCompanyEventPayload(orderCancelledEvent);
        orderOutboxHelper.createAndSaveOutboxMessage(orderCompanyEventPayload, orderId, ORDER_COMPANY_CANCEL_SAGA_NAME);


    }

}
