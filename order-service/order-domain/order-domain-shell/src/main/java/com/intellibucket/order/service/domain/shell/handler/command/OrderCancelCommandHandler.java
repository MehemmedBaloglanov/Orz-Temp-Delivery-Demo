package com.intellibucket.order.service.domain.shell.handler.command;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.order.service.domain.core.event.OrderCancelledEvent;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.service.OrderDomainService;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderCancelCommand;
import com.intellibucket.order.service.domain.shell.helper.OrderOutboxHelper;
import com.intellibucket.order.service.domain.shell.helper.OrderRepositoryHelper;
import com.intellibucket.order.service.domain.shell.mapper.OrderShellDataMapper;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.company.OrderCompanyRefundEventPayload;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.payment.OrderPaymentRefundEventPayload;
import com.intellibucket.order.service.domain.shell.security.AbstractSecurityContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.intellibucket.saga.order.SagaConstants.ORDER_COMPANY_REFUND_SAGA_NAME;
import static com.intellibucket.saga.order.SagaConstants.ORDER_PAYMENT_REFUND_SAGA_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCancelCommandHandler {

    private final OrderDomainService orderDomainService;
    private final AbstractSecurityContextHolder securityContextHolder;
    private final OrderRepositoryHelper orderRepositoryHelper;
    private final OrderShellDataMapper orderShellDataMapper;
    private final OrderOutboxHelper orderOutboxHelper;


    @Transactional
    public void handle(OrderCancelCommand command) throws OrderDomainException {

        UserID currentUserID = securityContextHolder.currentUserID();

        OrderID orderId = OrderID.of(command.getOrderId());

        OrderRoot orderRoot = orderRepositoryHelper.findOrderById(orderId);

        if (!orderRoot.getUserId().equals(currentUserID)) {
            log.error("User: {} is not authorized to order: {} cancel", currentUserID, orderId);
            throw new OrderDomainException("User: " + currentUserID + " is not authorized to order: " + orderId + " cancel");
        }

        orderRoot.cancelByCustomer();
        OrderCancelledEvent orderCancelEvent = orderDomainService.orderCustomerCancel(orderRoot, command.getCancelMessage());
        orderRepositoryHelper.saveOrder(orderRoot);

        OrderPaymentRefundEventPayload orderCancelEventPayload = orderShellDataMapper.orderCancelledEventToOrderPaymentCancelEventPayload(orderCancelEvent);
        orderOutboxHelper.createAndSaveOutboxMessage(orderCancelEventPayload, orderId, ORDER_PAYMENT_REFUND_SAGA_NAME);

        OrderCompanyRefundEventPayload orderCompanyEventPayload = orderShellDataMapper.orderCancelledEventToOrderCompanyCancelEventPayload(orderCancelEvent);
        orderOutboxHelper.createAndSaveOutboxMessage(orderCompanyEventPayload, orderId, ORDER_COMPANY_REFUND_SAGA_NAME);

    }
}
