package com.intellibucket.order.service.domain.shell.handler.command;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.order.service.domain.core.event.StartDeliveryOrderEvent;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.service.OrderDomainService;
import com.intellibucket.order.service.domain.shell.dto.rest.command.OrderStartDeliveryCommand;
import com.intellibucket.order.service.domain.shell.helper.OrderOutboxHelper;
import com.intellibucket.order.service.domain.shell.helper.OrderRepositoryHelper;
import com.intellibucket.order.service.domain.shell.mapper.OrderShellDataMapper;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.delivery.OrderStartDeliveryEventPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.intellibucket.saga.order.SagaConstants.ORDER_START_DELIVERY_SAGA_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderStartDeliveryCommandHandler {

    private final OrderRepositoryHelper orderRepositoryHelper;
    private final OrderDomainService orderDomainService;
    private final OrderShellDataMapper orderShellDataMapper;
    private final OrderOutboxHelper orderOutboxHelper;

    @Transactional
    public void handle(OrderStartDeliveryCommand command) throws OrderDomainException {

        OrderID orderId = OrderID.of(command.getOrderId());

        OrderRoot orderRoot = orderRepositoryHelper.findOrderById(orderId);

        StartDeliveryOrderEvent startDeliveryOrderEvent = orderDomainService.startDelivery(orderRoot);
        OrderStartDeliveryEventPayload orderStartDeliveryEventPayload = orderShellDataMapper.startDeliveryOrderEventToOrderStartDeliveryEventPayload(startDeliveryOrderEvent);
        orderOutboxHelper.createAndSaveOutboxMessage(orderStartDeliveryEventPayload, orderId, ORDER_START_DELIVERY_SAGA_NAME);
        orderRepositoryHelper.saveOrder(orderRoot);

    }
}
