package com.intellibucket.order.service.domain.shell.outbox.scheduler.abstracts;

import com.intellibucket.outbox.OutboxScheduler;
import com.intellibucket.outbox.OutboxStatus;
import com.intellibucket.message.model.BaseMessageModel;
import com.intellibucket.message.publisher.BaseMessagePublisher;
import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.port.output.repository.BaseOutboxRepository;
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
public abstract class AbstractOutboxSchedulerProcessor<R extends BaseOutboxRepository<T>, P extends BaseMessagePublisher<T>, T extends BaseMessageModel> implements OutboxScheduler {


    protected final R outboxRepository;
    protected final P eventPublisher;

    @Override
    @Transactional
    @Scheduled(fixedDelayString = "${order-service.outbox-scheduler-fixed-rate}", initialDelayString = "${order-service.outbox-scheduler-initial-delay}")
    public void processOutboxMessage() throws OrderDomainException{
        Optional<List<T>> outboxMessagesResponse = outboxRepository.findByOutboxStatus(OutboxStatus.STARTED);
        if (outboxMessagesResponse.isPresent()) {
            List<T> outboxMessages = outboxMessagesResponse.get();

            log.info("Received {} {} with ids: {}, sending to message bus!",
                    outboxMessages.size(),
                    outboxMessages.stream().findFirst().getClass().getSimpleName(),
                    outboxMessages.stream().map(outboxMessage -> outboxMessage.getId().toString()).collect(Collectors.joining(",")));

            for (T outboxMessage : outboxMessages) {
                eventPublisher.publish(outboxMessage, this::updateOutboxStatus);
            }
            log.info("{} {} sent to message bus!", outboxMessages.size(), outboxMessages.stream().findFirst().getClass().getSimpleName());

        }
    }

    protected void updateOutboxStatus(T outboxMessage, OutboxStatus outboxStatus) {
        outboxMessage.setOutboxStatus(outboxStatus);
        try {
            T response = outboxRepository.save(outboxMessage);
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
