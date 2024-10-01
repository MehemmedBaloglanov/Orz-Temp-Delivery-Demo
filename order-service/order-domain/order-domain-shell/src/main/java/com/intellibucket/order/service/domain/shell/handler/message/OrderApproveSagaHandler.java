package com.intellibucket.order.service.domain.shell.handler.message;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.order.service.domain.core.event.OrderCancelledEvent;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.service.OrderDomainService;
import com.intellibucket.order.service.domain.shell.dto.message.ApproveResponse;
import com.intellibucket.order.service.domain.shell.helper.OrderOutboxHelper;
import com.intellibucket.order.service.domain.shell.helper.OrderRepositoryHelper;
import com.intellibucket.order.service.domain.shell.mapper.OrderShellMapper;
import com.intellibucket.order.service.domain.shell.outbox.model.OutboxMessage;
import com.intellibucket.order.service.domain.shell.port.output.repository.OutboxRepository;
import com.intellibucket.saga.SagaStep;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderApproveSagaHandler implements SagaStep<ApproveResponse> {
    private final OrderRepositoryHelper orderRepositoryHelper;
    private final OrderDomainService orderDomainService;
    private final OrderShellMapper orderShellMapper;
    private final OutboxRepository outboxRepository;
    private final OrderOutboxHelper orderOutboxHelper;

    @Override
    @Transactional
    public void process(ApproveResponse data) throws OrderDomainException {
        OrderID orderID = OrderID.of(data.getOrderId());
        OrderRoot orderRoot = orderRepositoryHelper.findOrderById(orderID);
        orderDomainService.approveOrder(orderRoot);
        orderRepositoryHelper.saveOrder(orderRoot);

    }

    @Override
    @Transactional
    public void rollback(ApproveResponse data) throws OrderDomainException {
        OrderID orderID = OrderID.of(data.getOrderId());
        OrderRoot orderRoot = orderRepositoryHelper.findOrderById(orderID);
        OrderCancelledEvent orderCancelledEvent = orderDomainService.orderPaymentCancel(orderRoot, data.getFailureMessage());
        orderRepositoryHelper.saveOrder(orderRoot);
        OutboxMessage outboxMessage = orderShellMapper.orderCancelledEventToOutboxMessage(orderCancelledEvent);
        orderCancelPaymentOutboxRepository.save(outboxMessage);

    }

}
