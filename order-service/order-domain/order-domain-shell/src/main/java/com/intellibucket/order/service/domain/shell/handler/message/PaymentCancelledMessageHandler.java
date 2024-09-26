package com.intellibucket.order.service.domain.shell.handler.message;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.order.service.domain.core.event.OrderCancelledEvent;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.exception.OrderNotFoundException;
import com.intellibucket.order.service.domain.core.root.OrderRoot;
import com.intellibucket.order.service.domain.core.service.OrderDomainService;
import com.intellibucket.order.service.domain.core.valueobject.OrderCancelType;
import com.intellibucket.order.service.domain.shell.dto.message.PaymentResponse;
import com.intellibucket.order.service.domain.shell.helper.OrderOutboxHelper;
import com.intellibucket.order.service.domain.shell.helper.OrderSagaHelper;
import com.intellibucket.order.service.domain.shell.outbox.model.message.OrderPaymentOutboxMessage;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentCancelledMessageHandler {

    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final OrderSagaHelper orderSagaHelper;
    private final OrderOutboxHelper orderOutboxHelper;

    @Transactional
    public void handle(PaymentResponse paymentResponse) throws OrderDomainException {
        Optional<OrderPaymentOutboxMessage> paymentOutboxMessageOptional = orderOutboxHelper.findByTypeAndSagaIdAndSagaStatus(UUID.fromString(paymentResponse.getSagaId()), orderSagaHelper.getCurrentSagaStatus(paymentResponse.getPaymentStatus()));

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

        orderOutboxHelper.cancelAndSavePaymentOutboxMessage(orderPaymentOutboxMessage, orderCancelledEvent);

        //FIXME company servise sifarisin legv olundugunu bildir

        log.info("Order with id: {} is cancelled", order.getRootID().value());

    }
}
