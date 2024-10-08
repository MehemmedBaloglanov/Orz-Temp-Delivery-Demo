package com.intellibucket.company.service.domain.shell.outbox.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.outbox.model.OutboxMessage;
import com.intellibucket.company.service.domain.shell.outbox.model.payload.BaseEventPayload;
import com.intellibucket.company.service.domain.shell.port.output.repository.OutboxRepository;
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
public class CompanyOutboxHelper {

    private final ObjectMapper objectMapper;
    private final OutboxRepository outboxRepository;


    private String createPayload(BaseEventPayload payload) throws CompanyDomainException {
        try {
            return objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new CompanyDomainException("Could not create object");
        }
    }

    private void save(OutboxMessage outboxMessage) throws CompanyDomainException {
        OutboxMessage response = outboxRepository.save(outboxMessage);
        if (response == null) {
            log.error("Could not save {} with outbox id: {}", outboxMessage.getClass().getSimpleName(), outboxMessage.getId());
            throw new CompanyDomainException("Could not save " + outboxMessage.getClass().getSimpleName() + " with outbox id: " + outboxMessage.getId());
        }
        log.info("{} saved with outbox id: {}", outboxMessage.getClass().getSimpleName(), outboxMessage.getId());
    }


    @Transactional
    public void createAndSaveOutboxMessage(BaseEventPayload payload, String sagaName) throws CompanyDomainException {
        OutboxMessage outboxMessage = OutboxMessage.builder()
                .id(UUID.randomUUID())
                .payload(createPayload(payload))
                .createdAt(OffsetDateTime.now(ZONE_ID))
                .sagaName(sagaName)
                .outboxStatus(OutboxStatus.STARTED)
                .build();
        save(outboxMessage);
    }
}
