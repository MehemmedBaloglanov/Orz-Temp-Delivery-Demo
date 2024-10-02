package com.intellibucket.order.service.domain.shell.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.outbox.model.OutboxMessage;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.BaseEventPayload;
import com.intellibucket.order.service.domain.shell.port.output.repository.OutboxRepository;
import com.intellibucket.outbox.OutboxStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

import static com.intellibucket.domain.constants.DomainConstants.ZONE_ID;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderOutboxHelper {

    private final ObjectMapper objectMapper;
    private final OutboxRepository outboxRepository;


    private String createPayload(BaseEventPayload payload, OrderID orderId) throws OrderDomainException {
        try {
            return objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            log.error("Could not create OrderPaymentEventPayload object for order id: {}", orderId);
            throw new OrderDomainException("Could not create OrderPaymentEventPayload object for order id: " + orderId);
        }
    }

    private void save(OutboxMessage outboxMessage) throws OrderDomainException {
        OutboxMessage response = outboxRepository.save(outboxMessage);
        if (response == null) {
            log.error("Could not save {} with outbox id: {}", outboxMessage.getClass().getSimpleName(), outboxMessage.getId());
            throw new OrderDomainException("Could not save " + outboxMessage.getClass().getSimpleName() + " with outbox id: " + outboxMessage.getId());
        }
        log.info("{} saved with outbox id: {}", outboxMessage.getClass().getSimpleName(), outboxMessage.getId());
    }


    @Transactional
    public void createAndSaveOutboxMessage(BaseEventPayload payload, OrderID orderId, String sagaName) throws OrderDomainException {
        OutboxMessage outboxMessage = OutboxMessage.builder()
                .id(UUID.randomUUID())
                .payload(createPayload(payload, orderId))
                .createdAt(OffsetDateTime.now(ZONE_ID))
                .sagaName(sagaName)
                .outboxStatus(OutboxStatus.STARTED)
                .build();
        save(outboxMessage);
    }
}
