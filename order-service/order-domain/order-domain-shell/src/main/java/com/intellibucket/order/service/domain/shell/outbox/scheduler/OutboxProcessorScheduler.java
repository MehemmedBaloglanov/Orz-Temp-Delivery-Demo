package com.intellibucket.order.service.domain.shell.outbox.scheduler;

import com.intellibucket.domain.exception.DomainException;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.outbox.model.OutboxMessage;
import com.intellibucket.order.service.domain.shell.port.output.publisher.*;
import com.intellibucket.order.service.domain.shell.port.output.repository.OutboxRepository;
import com.intellibucket.outbox.OutboxScheduler;
import com.intellibucket.outbox.OutboxStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.intellibucket.domain.constants.DomainConstants.ZONE_ID;
import static com.intellibucket.saga.order.SagaConstants.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxProcessorScheduler implements OutboxScheduler {

    private final OutboxRepository outboxRepository;

    private final AbstractOrderCompletedEventPublisher orderCompletedEventPublisher;
    private final AbstractOrderApproveEventPublisher orderApproveEventPublisher;
    private final AbstractOrderStartDeliveryEventPublisher orderStartDeliveryEventPublisher;
    private final AbstractOrderPaymentRefundEventPublisher abstractOrderPaymentRefundEventPublisher;
    private final AbstractOrderCompanyRefundEventPublisher abstractOrderCompanyRefundEventPublisher;

    @Override
    @Transactional
    @Scheduled(fixedDelayString = "${order-service.outbox-scheduler-fixed-rate}", initialDelayString = "${order-service.outbox-scheduler-initial-delay}")
    public void processOutboxMessage() throws DomainException {
        Optional<List<OutboxMessage>> outboxMessagesResponse = outboxRepository.findByOutboxStatus(OutboxStatus.STARTED);
        if (outboxMessagesResponse.isPresent()) {
            List<OutboxMessage> outboxMessages = outboxMessagesResponse.get();

            log.info("Received {} {} with ids: {}, sending to message bus!",
                    outboxMessages.size(),
                    outboxMessages.stream().findFirst().getClass().getSimpleName(),
                    outboxMessages.stream().map(outboxMessage -> outboxMessage.getId().toString()).collect(Collectors.joining(",")));

            for (OutboxMessage outboxMessage : outboxMessages) {
                switch (outboxMessage.getSagaName()) {
                    case ORDER_START_DELIVERY_SAGA_NAME ->
                            orderStartDeliveryEventPublisher.publish(outboxMessage, this::updateOutboxStatus);
                    case ORDER_COMPLETED_SAGA_NAME ->
                            orderCompletedEventPublisher.publish(outboxMessage, this::updateOutboxStatus);
                    case ORDER_APPROVE_SAGA_NAME ->
                            orderApproveEventPublisher.publish(outboxMessage, this::updateOutboxStatus);
                    case ORDER_PAYMENT_REFUND_SAGA_NAME ->
                            abstractOrderPaymentRefundEventPublisher.publish(outboxMessage, this::updateOutboxStatus);
                    case ORDER_COMPANY_REFUND_SAGA_NAME ->
                            abstractOrderCompanyRefundEventPublisher.publish(outboxMessage, this::updateOutboxStatus);
                    default -> {
                        log.error("Unknown saga name: {}", outboxMessage.getSagaName());
                        throw new OrderDomainException("Unknown saga name: " + outboxMessage.getSagaName());
                    }
                }

            }
            log.info("{} {} sent to message bus!", outboxMessages.size(), outboxMessages.stream().findFirst().getClass().getSimpleName());

        }
    }

    private void updateOutboxStatus(OutboxMessage outboxMessage, OutboxStatus outboxStatus) {
        outboxMessage.setOutboxStatus(outboxStatus);
        outboxMessage.setProcessedAt(OffsetDateTime.now(ZONE_ID));
        try {
            OutboxMessage response = outboxRepository.save(outboxMessage);
            if (response == null) {
                log.error("Could not save {} with outbox id: {}", outboxMessage.getClass().getSimpleName(), outboxMessage.getId());
                throw new OrderDomainException("Could not save " + outboxMessage.getClass().getSimpleName() + " with outbox id: " + outboxMessage.getId());
            }
            log.info("{} is updated with outbox status: {}", outboxMessage.getClass().getSimpleName(), outboxStatus.name());
        } catch (OrderDomainException e) {
            log.error(e.getMessage());
        }
    }
}
