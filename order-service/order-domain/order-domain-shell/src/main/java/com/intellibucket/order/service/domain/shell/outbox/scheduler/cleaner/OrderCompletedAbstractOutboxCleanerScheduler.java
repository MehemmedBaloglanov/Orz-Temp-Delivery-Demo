package com.intellibucket.order.service.domain.shell.outbox.scheduler.cleaner;

import com.intellibucket.order.service.domain.shell.outbox.model.message.OrderCompletedEventOutboxMessage;
import com.intellibucket.order.service.domain.shell.outbox.scheduler.abstracts.AbstractOutboxCleanerScheduler;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderCompleteOutboxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderCompletedAbstractOutboxCleanerScheduler extends AbstractOutboxCleanerScheduler<OrderCompleteOutboxRepository, OrderCompletedEventOutboxMessage> {

    public OrderCompletedAbstractOutboxCleanerScheduler(OrderCompleteOutboxRepository outboxRepository) {
        super(outboxRepository);
    }

}
