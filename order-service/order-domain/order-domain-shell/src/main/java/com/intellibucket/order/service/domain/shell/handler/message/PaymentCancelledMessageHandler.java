package com.intellibucket.order.service.domain.shell.handler.message;

import com.food.ordering.system.saga.SagaStatus;
import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.order.service.domain.core.event.OrderCancelledEvent;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.exception.OrderNotFoundException;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.service.OrderDomainService;
import com.intellibucket.order.service.domain.core.valueobject.OrderCancelType;
import com.intellibucket.order.service.domain.shell.dto.message.PaymentResponse;
import com.intellibucket.order.service.domain.shell.helper.OrderSagaHelper;
import com.intellibucket.order.service.domain.shell.helper.PaymentOutboxHelper;
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
public class PaymentCancelledMessageHandler {

    private final OrderRepository orderRepository;
    private final OrderDomainService orderDomainService;
    private final OrderPaymentOutboxRepository orderPaymentOutboxRepository;
    private final OrderSagaHelper orderSagaHelper;
    private final PaymentOutboxHelper paymentOutboxHelper;

    @Transactional
    public void handle(PaymentResponse paymentResponse) throws OrderDomainException {
        Optional<OrderPaymentOutboxMessage> paymentOutboxMessageOptional = orderPaymentOutboxRepository.findByTypeAndSagaIdAndSagaStatus(
                ORDER_SAGA_NAME,
                UUID.fromString(paymentResponse.getSagaId()),
                orderSagaHelper.getCurrentSagaStatus(paymentResponse.getPaymentStatus()));

        if (paymentOutboxMessageOptional.isEmpty()) {
            log.info("An outbox message with saga id: {} is already roll backed!", paymentResponse.getSagaId());
            return;
        }

        OrderPaymentOutboxMessage orderPaymentOutboxMessage = paymentOutboxMessageOptional.get();
        OrderID orderID = OrderID.of(paymentResponse.getOrderId());

        Optional<OrderRoot> orderRootOptional = orderRepository.findById(orderID);

        if (orderRootOptional.isEmpty()) {
            log.error("Order with id: {} could not be found!", orderID);
            throw new OrderNotFoundException("Order with id " + orderID + " could not be found!");
        }

        OrderRoot order = orderRootOptional.get();

        OrderCancelledEvent orderCancelledEvent = orderDomainService.orderPaymentCancel(order, OrderCancelType.SYSTEM, paymentResponse.getFailureMessages());
        orderRepository.save(order);

        orderPaymentOutboxMessage.setProcessedAt(OffsetDateTime.now(ZONE_ID));
        orderPaymentOutboxMessage.setOrderStatus(order.getStatus());
        SagaStatus sagaStatus = orderSagaHelper.orderStatusToSagaStatus(orderCancelledEvent.getOrderRoot().getStatus());

        orderPaymentOutboxMessage.setSagaStatus(sagaStatus);

        paymentOutboxHelper.save(orderPaymentOutboxMessage);

        //FIXME company servise sifarisin legv olundugunu bildir

        log.info("Order with id: {} is cancelled", order.getRootID().value());

    }
}
