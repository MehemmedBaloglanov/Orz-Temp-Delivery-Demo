package com.intellibucket.order.service.domain.shell.outbox.scheduler.abstracts;

import com.intellibucket.outbox.OutboxScheduler;
import com.intellibucket.outbox.OutboxStatus;
import com.intellibucket.message.model.BaseMessageModel;
import com.intellibucket.order.service.domain.shell.port.output.repository.BaseOutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractOutboxCleanerScheduler<R extends BaseOutboxRepository<RT>, RT extends BaseMessageModel> implements OutboxScheduler {
    protected final R outboxRepository;

    @Override
    @Transactional
    @Scheduled(cron = "@midnight")
    public void processOutboxMessage() {
        Optional<List<RT>> outboxMessageResponse = outboxRepository.findByOutboxStatus(OutboxStatus.COMPLETED);
        if (outboxMessageResponse.isPresent()) {
            List<RT> outboxMessages = outboxMessageResponse.get();
            log.info("Received {} {} for clean-up. The payloads: {}",
                    outboxMessages.size(),
                    outboxMessages.stream().findFirst().getClass().getSimpleName(),
                    outboxMessages.stream().map(RT::getPayload).collect(Collectors.joining("\n")));
            outboxRepository.deleteByOutboxStatus(OutboxStatus.COMPLETED);
            log.info("{} {} deleted!", outboxMessages.size(), outboxMessages.stream().findFirst().getClass().getSimpleName());
        }
    }
}
