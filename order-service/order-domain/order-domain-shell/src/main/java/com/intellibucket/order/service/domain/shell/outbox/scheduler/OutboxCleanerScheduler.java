package com.intellibucket.order.service.domain.shell.outbox.scheduler;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.outbox.model.OutboxMessage;
import com.intellibucket.order.service.domain.shell.port.output.repository.OutboxRepository;
import com.intellibucket.outbox.OutboxScheduler;
import com.intellibucket.outbox.OutboxStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxCleanerScheduler implements OutboxScheduler {

    private final OutboxRepository outboxRepository;

    @Override
    @Transactional
    @Scheduled(fixedDelayString = "${order-service.outbox-scheduler-fixed-rate}", initialDelayString = "${order-service.outbox-scheduler-initial-delay}")
    public void processOutboxMessage() throws OrderDomainException {
        Optional<List<OutboxMessage>> outboxMessagesResponse = outboxRepository.findByOutboxStatus(OutboxStatus.COMPLETED);
        if (outboxMessagesResponse.isPresent()) {
            List<OutboxMessage> outboxMessages = outboxMessagesResponse.get();
            log.info("Received {} OrderPaymentOutboxMessage for clean-up. The payloads: {}",
                    outboxMessages.size(),
                    outboxMessages.stream().map(OutboxMessage::getPayload)
                            .collect(Collectors.joining("\n")));

            outboxRepository.deleteByOutboxStatus(OutboxStatus.COMPLETED);
            log.info("{} {} sent to message bus!", outboxMessages.size(), outboxMessages.stream().findFirst().getClass().getSimpleName());

        }
    }

}
