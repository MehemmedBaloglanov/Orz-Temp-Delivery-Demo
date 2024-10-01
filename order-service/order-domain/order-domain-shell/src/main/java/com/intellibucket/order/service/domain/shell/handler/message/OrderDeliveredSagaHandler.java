package com.intellibucket.order.service.domain.shell.handler.message;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.order.service.domain.core.event.OrderCancelledEvent;
import com.intellibucket.order.service.domain.core.event.OrderCompletedEvent;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.service.OrderDomainService;
import com.intellibucket.order.service.domain.shell.dto.message.DeliveryResponse;
import com.intellibucket.order.service.domain.shell.helper.OrderOutboxHelper;
import com.intellibucket.order.service.domain.shell.helper.OrderRepositoryHelper;
import com.intellibucket.order.service.domain.shell.mapper.OrderShellMapper;
import com.intellibucket.order.service.domain.shell.outbox.model.OutboxMessage;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.company.OrderCompanyEventPayload;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.payment.OrderPaymentCancelEventPayload;
import com.intellibucket.order.service.domain.shell.port.output.repository.OutboxRepository;
import com.intellibucket.saga.SagaStep;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.intellibucket.saga.order.SagaConstants.ORDER_COMPLETED_SAGA_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderDeliveredSagaHandler implements SagaStep<DeliveryResponse> {

    private final OrderDomainService orderDomainService;
    private final OrderRepositoryHelper orderRepositoryHelper;
    private final OrderShellMapper orderShellMapper;
    private final OrderOutboxHelper orderOutboxHelper;


    @Override
    @Transactional
    public void process(DeliveryResponse data) throws OrderDomainException {

        OrderID orderID = OrderID.of(data.getOrderId());
        OrderRoot orderRoot = orderRepositoryHelper.findOrderById(orderID);
        OrderCompletedEvent orderCompletedEvent = orderDomainService.orderComplete(orderRoot);
        orderRepositoryHelper.saveOrder(orderCompletedEvent.getOrderRoot());

        OrderCompanyEventPayload orderCompanyEventPayload = orderShellMapper.orderCompletedEventToOrderCompanyEventPayload(orderCompletedEvent);

        orderOutboxHelper.createAndSaveOutboxMessage(orderCompanyEventPayload, orderID, ORDER_COMPLETED_SAGA_NAME);


    }

    @Override
    @Transactional
    public void rollback(DeliveryResponse data) throws OrderDomainException {
        OrderID orderID = OrderID.of(data.getOrderId());
        OrderRoot orderRoot = orderRepositoryHelper.findOrderById(orderID);
        OrderCancelledEvent orderCancelledEvent = orderDomainService.orderPaymentCancel(orderRoot, data.getFailureMessage());
        orderRepositoryHelper.saveOrder(orderCancelledEvent.getOrderRoot());
        OrderPaymentCancelEventPayload orderPaymentCancelEventPayload = orderShellMapper.orderCancelledEventToOrderPaymentCancelEventPayload(orderCancelledEvent);
        orderOutboxHelper.createAndSaveOutboxMessage(orderPaymentCancelEventPayload, orderID, ORDER_COMPLETED_SAGA_NAME);
    }
}
