package com.intellibucket.order.service.domain.shell.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellibucket.message.model.BaseMessageModel;
import com.intellibucket.order.service.domain.core.event.OrderCancelledEvent;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.core.valueobject.OrderStatus;
import com.intellibucket.order.service.domain.shell.outbox.model.message.OrderCancelPaymentEventOutboxMessage;
import com.intellibucket.order.service.domain.shell.outbox.model.message.OrderPaymentOutboxMessage;
import com.intellibucket.order.service.domain.shell.outbox.model.message.OrderStartDeliveryEventOutboxMessage;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.BaseEventPayload;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.OrderCancelPaymentEventPayload;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.OrderPaymentEventPayload;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.OrderStartDeliveryEventPayload;
import com.intellibucket.order.service.domain.shell.port.output.repository.BaseOutboxRepository;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderCancelPaymentOutboxRepository;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderPaymentOutboxRepository;
import com.intellibucket.outbox.OutboxStatus;
import com.intellibucket.saga.SagaStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import static com.intellibucket.constants.DomainConstants.ZONE_ID;
import static com.intellibucket.saga.order.SagaConstants.ORDER_SAGA_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderOutboxHelper {

    private final OrderPaymentOutboxRepository orderPaymentOutboxRepository;
    private final OrderCancelPaymentOutboxRepository orderCancelPaymentOutboxRepository;
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
    public void createAndSavePaymentOutboxMessage(OrderPaymentEventPayload paymentEventPayload) throws OrderDomainException {
        OrderPaymentOutboxMessage outboxMessage = OrderPaymentOutboxMessage.builder()
                .id(UUID.randomUUID())
                .sagaId(UUID.randomUUID())
                .payload(createPayload(paymentEventPayload))
                .orderStatus(OrderStatus.CREATED)
                .sagaStatus(SagaStatus.COMPENSATING)
                .outboxStatus(OutboxStatus.STARTED)
                .createdAt(paymentEventPayload.getCreatedAt())
                .build();
        save(outboxMessage, orderPaymentOutboxRepository);
    }

    @Transactional
    public void cancelAndSavePaymentOutboxMessage(OrderPaymentOutboxMessage orderPaymentOutboxMessage, OrderCancelledEvent orderCancelledEvent) throws OrderDomainException {

        orderPaymentOutboxMessage.setProcessedAt(OffsetDateTime.now(ZONE_ID));
        orderPaymentOutboxMessage.setOrderStatus(orderCancelledEvent.getOrderRoot().getStatus());
        SagaStatus sagaStatus = orderSagaHelper.orderStatusToSagaStatus(orderCancelledEvent.getOrderRoot().getStatus());
        orderPaymentOutboxMessage.setSagaStatus(sagaStatus);
        save(orderPaymentOutboxMessage, orderPaymentOutboxRepository);
    }


    @Transactional
    public void createAndSavePaymentCancelOutboxMessage(OrderCancelPaymentEventPayload orderCancelEventPayload) throws OrderDomainException {
        OrderCancelPaymentEventOutboxMessage outboxMessage = OrderCancelPaymentEventOutboxMessage.builder()
                .id(UUID.randomUUID())
                .sagaId(UUID.randomUUID())
                .payload(createPayload(orderCancelEventPayload))
                .sagaStatus(orderSagaHelper.orderStatusToSagaStatus(OrderStatus.CREATED))
                .createdAt(orderCancelEventPayload.getCreatedAt())
                .outboxStatus(OutboxStatus.STARTED)
                .build();
        save(outboxMessage, orderCancelPaymentOutboxRepository);
    }

    public void createAndSaveOrderStartDeliveryOutboxMessage(OrderStartDeliveryEventPayload orderStartDeliveryEventPayload) throws OrderDomainException {
        OrderStartDeliveryEventOutboxMessage message = OrderStartDeliveryEventOutboxMessage.builder()
                .id(UUID.randomUUID())
                .sagaId(UUID.randomUUID())
                .payload(createPayload(orderStartDeliveryEventPayload))
                .createdAt(orderStartDeliveryEventPayload.getCreatedAt())
                .outboxStatus(OutboxStatus.STARTED)
                .build();
    }

    private <T extends BaseEventPayload> String createPayload(T paymentEventPayload) throws OrderDomainException {
        try {
            return objectMapper.writeValueAsString(paymentEventPayload);
        } catch (JsonProcessingException e) {
            log.error("Could not create OrderPaymentEventPayload object for order id: {}", paymentEventPayload.getOrderId(), e);
            throw new OrderDomainException("Could not create OrderPaymentEventPayload object for order id: " + paymentEventPayload.getOrderId(), e);
        }
    }

    private <M extends BaseMessageModel, R extends BaseOutboxRepository<M>> void save(M outboxMessage, R repository) throws OrderDomainException {
        M response = repository.save(outboxMessage);
        if (response == null) {
            log.error("Could not save {} with outbox id: {}", outboxMessage.getClass().getSimpleName(), outboxMessage.getId());
            throw new OrderDomainException("Could not save " + outboxMessage.getClass().getSimpleName() + " with outbox id: " + outboxMessage.getId());
        }
        log.info("{} saved with outbox id: {}", outboxMessage.getClass().getSimpleName(), outboxMessage.getId());
    }
}
