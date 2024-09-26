package com.intellibucket.order.service.domain.shell.outbox.scheduler.cleaner;


import com.intellibucket.outbox.OutboxStatus;
import com.intellibucket.saga.SagaStatus;
import com.intellibucket.order.service.domain.shell.outbox.model.message.OrderPaymentOutboxMessage;
import com.intellibucket.order.service.domain.shell.outbox.scheduler.abstracts.AbstractOutboxCleanerScheduler;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderPaymentOutboxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.intellibucket.saga.order.SagaConstants.ORDER_SAGA_NAME;

@Slf4j
@Component
public class OrderPaymentOutboxCleanerScheduler extends AbstractOutboxCleanerScheduler<OrderPaymentOutboxRepository, OrderPaymentOutboxMessage> {


    public OrderPaymentOutboxCleanerScheduler(OrderPaymentOutboxRepository outboxRepository) {
        super(outboxRepository);
    }

    @Override
    @Scheduled(cron = "@midnight")
    public void processOutboxMessage() {
        SagaStatus[] sagaStatuses = new SagaStatus[]{SagaStatus.SUCCEEDED, SagaStatus.FAILED, SagaStatus.COMPENSATED};

        Optional<List<OrderPaymentOutboxMessage>> outboxMessagesResponse = outboxRepository.findByTypeAndOutboxStatusAndSagaStatus(ORDER_SAGA_NAME, OutboxStatus.COMPLETED, sagaStatuses);

        if (outboxMessagesResponse.isPresent()) {
            List<OrderPaymentOutboxMessage> outboxMessages = outboxMessagesResponse.get();
            log.info("Received {} OrderPaymentOutboxMessage for clean-up. The payloads: {}",
                    outboxMessages.size(),
                    outboxMessages.stream().map(OrderPaymentOutboxMessage::getPayload)
                            .collect(Collectors.joining("\n")));

            outboxRepository.deleteByTypeAndOutboxStatusAndSagaStatus(ORDER_SAGA_NAME, OutboxStatus.COMPLETED, sagaStatuses);

            log.info("{} OrderPaymentOutboxMessage deleted!", outboxMessages.size());
        }

    }
}
