package com.intellibucket.order.service.domain.shell.handler.message;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.order.service.domain.core.event.OrderPaidEvent;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.service.OrderDomainService;
import com.intellibucket.order.service.domain.shell.dto.message.PaymentResponse;
import com.intellibucket.order.service.domain.shell.helper.OrderOutboxHelper;
import com.intellibucket.order.service.domain.shell.helper.OrderRepositoryHelper;
import com.intellibucket.order.service.domain.shell.mapper.OrderShellMapper;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.company.OrderCompanyApproveEventPayload;
import com.intellibucket.saga.SagaStep;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.intellibucket.saga.order.SagaConstants.ORDER_APPROVE_SAGA_NAME;


@Slf4j
@Component
@RequiredArgsConstructor
public class OrderPaymentResponseSagaHandler implements SagaStep<PaymentResponse> {

    private final OrderRepositoryHelper orderRepositoryHelper;
    private final OrderDomainService orderDomainService;
    private final OrderShellMapper orderShellMapper;
    private final OrderOutboxHelper orderOutboxHelper;

    @Override
    @Transactional
    public void process(PaymentResponse data) throws OrderDomainException {

        OrderID orderId = OrderID.of(data.getOrderId());
        log.info("Received payment complete event for order id: {}", orderId);

        OrderRoot orderRoot = orderRepositoryHelper.findOrderById(orderId);
        OrderPaidEvent orderPaidEvent = orderDomainService.orderPay(orderRoot);
        orderRepositoryHelper.saveOrder(orderRoot);

        log.info("Saving OrderApproveEventOutboxMessage for order with id: {}", data.getOrderId());
        OrderCompanyApproveEventPayload payload = orderShellMapper.orderPaidEventToOrderCompanyApproveEventPayload(orderPaidEvent);
        orderOutboxHelper.createAndSaveOutboxMessage(payload, orderId, ORDER_APPROVE_SAGA_NAME);
        log.info("Completing payment for order with id: {}", data.getOrderId());

    }

    @Override
    @Transactional
    public void rollback(PaymentResponse data) throws OrderDomainException {
        OrderID orderID = OrderID.of(data.getOrderId());
        log.info("Received payment cancel event for order id: {}", orderID);
        OrderRoot orderRoot = orderRepositoryHelper.findOrderById(orderID);
        orderDomainService.orderCancel(orderRoot, data.getFailureMessage());
        orderRepositoryHelper.saveOrder(orderRoot);
        log.info("Cancelled payment for order with id: {}", data.getOrderId());
    }
}
