package com.intellibucket.order.service.domain.shell.handler.message;

import com.food.ordering.system.saga.SagaStatus;
import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.order.service.domain.core.event.OrderPaidEvent;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.exception.OrderNotFoundException;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.service.OrderDomainService;
import com.intellibucket.order.service.domain.shell.dto.message.PaymentResponse;
import com.intellibucket.order.service.domain.shell.helper.OrderSagaHelper;
import com.intellibucket.order.service.domain.shell.outbox.model.message.OrderPaymentOutboxMessage;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderRepository;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderPaymentOutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.food.ordering.system.saga.order.SagaConstants.ORDER_SAGA_NAME;
import static com.intellibucket.constants.DomainConstants.ZONE_ID;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentCompletedMessageHandler {

    private final OrderRepository orderRepository;
    private final OrderDomainService orderDomainService;
    private final OrderPaymentOutboxRepository orderPaymentOutboxRepository;
    private final OrderSagaHelper orderSagaHelper;

    @Transactional
    public void handle(PaymentResponse paymentResponse) throws OrderDomainException {

        SagaStatus[] sagaStatuses = {SagaStatus.STARTED};

        Optional<OrderPaymentOutboxMessage> paymentOutboxMessageOptional = orderPaymentOutboxRepository.findByTypeAndSagaIdAndSagaStatus(
                ORDER_SAGA_NAME,
                UUID.fromString(paymentResponse.getSagaId()),
                sagaStatuses);

        if (paymentOutboxMessageOptional.isEmpty()) {
            log.info("An outbox message with saga id: {} is already processed!", paymentResponse.getSagaId());
            return;
        }
        OrderPaymentOutboxMessage paymentOutboxMessage = paymentOutboxMessageOptional.get();

        OrderID orderID = OrderID.of(paymentOutboxMessage.getId());

        //complete order pay
        Optional<OrderRoot> orderRootOptional = orderRepository.findById(orderID);
        if (orderRootOptional.isEmpty()) {
            log.error("Order with id: {} could not be found!", orderID);
            throw new OrderNotFoundException("Order with id " + orderID + " could not be found!");
        }
        OrderRoot orderRoot = orderRootOptional.get();
        OrderPaidEvent domainEvent = orderDomainService.orderPay(orderRoot);
        orderRepository.save(orderRoot);
        //order completed pay

        // update payment Message
        SagaStatus sagaStatus = orderSagaHelper.orderStatusToSagaStatus(domainEvent.getOrderRoot().getStatus());
        paymentOutboxMessage.setProcessedAt(OffsetDateTime.now(ZONE_ID));
        paymentOutboxMessage.setOrderStatus(orderRoot.getStatus());
        paymentOutboxMessage.setSagaStatus(sagaStatus);
        orderPaymentOutboxRepository.save(paymentOutboxMessage);
        // end update payment message


        log.info("Order with id: {} is paid", domainEvent.getOrderRoot().getRootID());
    }
}
