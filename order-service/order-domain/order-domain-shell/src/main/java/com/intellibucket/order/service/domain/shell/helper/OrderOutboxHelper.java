package com.intellibucket.order.service.domain.shell.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellibucket.outbox.OutboxStatus;
import com.intellibucket.saga.SagaStatus;
import com.intellibucket.order.service.domain.core.event.OrderCancelledEvent;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.valueobject.OrderStatus;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.OrderPaymentEventPayload;
import com.intellibucket.order.service.domain.shell.outbox.model.message.OrderPaymentOutboxMessage;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderPaymentOutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.intellibucket.saga.order.SagaConstants.ORDER_SAGA_NAME;
import static com.intellibucket.constants.DomainConstants.ZONE_ID;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderOutboxHelper {

    private final OrderPaymentOutboxRepository orderPaymentOutboxRepository;
    private final ObjectMapper objectMapper;
    private final OrderSagaHelper orderSagaHelper;


    @Transactional
    public Optional<OrderPaymentOutboxMessage> findByTypeAndSagaIdAndSagaStatus(UUID sagaId, SagaStatus... sagaStatus) {
        return orderPaymentOutboxRepository.findByTypeAndSagaIdAndSagaStatus(
                ORDER_SAGA_NAME,
                sagaId,
                sagaStatus);
    }

    @Transactional
    public void save(OrderPaymentOutboxMessage orderPaymentOutboxMessage) throws OrderDomainException {
        OrderPaymentOutboxMessage response = orderPaymentOutboxRepository.save(orderPaymentOutboxMessage);
        if (response == null) {
            log.error("Could not save OrderPaymentOutboxMessage with outbox id: {}", orderPaymentOutboxMessage.getId());
            throw new OrderDomainException("Could not save OrderPaymentOutboxMessage with outbox id: " + orderPaymentOutboxMessage.getId());
        }
        log.info("OrderPaymentOutboxMessage saved with outbox id: {}", orderPaymentOutboxMessage.getId());
    }

    @Transactional
    public void createAndSavePaymentOutboxMessage(OrderPaymentEventPayload paymentEventPayload) throws OrderDomainException {
        OrderPaymentOutboxMessage outboxMessage = OrderPaymentOutboxMessage.builder()
                .id(UUID.randomUUID())
                .sagaId(UUID.randomUUID())
                .createdAt(paymentEventPayload.getCreatedAt())
                .type(ORDER_SAGA_NAME)
                .payload(createPayload(paymentEventPayload))
                .orderStatus(OrderStatus.CREATED)
                .sagaStatus(orderSagaHelper.orderStatusToSagaStatus(OrderStatus.CREATED))
                .outboxStatus(OutboxStatus.STARTED)
                .build();
        save(outboxMessage);
    }

    @Transactional
    public void cancelAndSavePaymentOutboxMessage(OrderPaymentOutboxMessage orderPaymentOutboxMessage, OrderCancelledEvent orderCancelledEvent) throws OrderDomainException {

        orderPaymentOutboxMessage.setProcessedAt(OffsetDateTime.now(ZONE_ID));
        orderPaymentOutboxMessage.setOrderStatus(orderCancelledEvent.getOrderRoot().getStatus());
        SagaStatus sagaStatus = orderSagaHelper.orderStatusToSagaStatus(orderCancelledEvent.getOrderRoot().getStatus());
        orderPaymentOutboxMessage.setSagaStatus(sagaStatus);
        save(orderPaymentOutboxMessage);
    }


    private String createPayload(OrderPaymentEventPayload paymentEventPayload) throws OrderDomainException {
        try {
            return objectMapper.writeValueAsString(paymentEventPayload);
        } catch (JsonProcessingException e) {
            log.error("Could not create OrderPaymentEventPayload object for order id: {}", paymentEventPayload.getOrderId(), e);
            throw new OrderDomainException("Could not create OrderPaymentEventPayload object for order id: " + paymentEventPayload.getOrderId(), e);
        }
    }

}
