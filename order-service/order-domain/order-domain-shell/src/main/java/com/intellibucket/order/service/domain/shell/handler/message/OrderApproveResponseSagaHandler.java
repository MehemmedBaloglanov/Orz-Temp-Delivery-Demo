package com.intellibucket.order.service.domain.shell.handler.message;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.order.service.domain.core.event.OrderCancelledEvent;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.service.OrderDomainService;
import com.intellibucket.order.service.domain.shell.dto.message.ApproveResponse;
import com.intellibucket.order.service.domain.shell.helper.OrderOutboxHelper;
import com.intellibucket.order.service.domain.shell.helper.OrderRepositoryHelper;
import com.intellibucket.order.service.domain.shell.mapper.OrderShellDataMapper;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.payment.OrderPaymentRefundEventPayload;
import com.intellibucket.saga.SagaStep;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.intellibucket.saga.order.SagaConstants.ORDER_COMPANY_REFUND_SAGA_NAME;
import static com.intellibucket.saga.order.SagaConstants.ORDER_PAYMENT_REFUND_SAGA_NAME;


@Slf4j
@Component
@RequiredArgsConstructor
public class OrderApproveResponseSagaHandler implements SagaStep<ApproveResponse> {
    private final OrderRepositoryHelper orderRepositoryHelper;
    private final OrderDomainService orderDomainService;
    private final OrderShellDataMapper orderShellDataMapper;
    private final OrderOutboxHelper orderOutboxHelper;

    @Override
    @Transactional
    public void process(ApproveResponse data) throws OrderDomainException {
        log.debug("Approving order {} for approval", data.getOrderId());
        OrderID orderID = OrderID.of(data.getOrderId());
        OrderRoot orderRoot = orderRepositoryHelper.findOrderById(orderID);
        log.trace("Order found: {}", orderRoot);
        orderDomainService.approveOrder(orderRoot);
        orderRepositoryHelper.saveOrder(orderRoot);

    }

    @Override
    @Transactional
    public void rollback(ApproveResponse data) throws OrderDomainException {
        log.debug("Rolling back order approve for order {}", data.getOrderId());

        OrderID orderID = OrderID.of(data.getOrderId());
        OrderRoot orderRoot = orderRepositoryHelper.findOrderById(orderID);
        log.trace("Order found: {}", orderRoot);

        OrderCancelledEvent orderCancelledEvent = orderDomainService.orderPaymentCancel(orderRoot, data.getFailureMessage());
        log.trace("Order cancelled event: {}", orderCancelledEvent);

        orderRepositoryHelper.saveOrder(orderRoot);
        OrderPaymentRefundEventPayload orderCancelledEventPayload = orderShellDataMapper.orderCancelledEventToOrderPaymentCancelEventPayload(orderCancelledEvent);
        log.trace("Outbox message created: {}", orderCancelledEventPayload);
        orderOutboxHelper.createAndSaveOutboxMessage(orderCancelledEventPayload, orderID, ORDER_PAYMENT_REFUND_SAGA_NAME);


    }

}
