package com.intellibucket.order.service.domain.shell.outbox.scheduler.processor;

import com.intellibucket.order.service.domain.shell.outbox.model.message.OrderCompletedEventOutboxMessage;
import com.intellibucket.order.service.domain.shell.outbox.scheduler.abstracts.AbstractOutboxSchedulerProcessor;
import com.intellibucket.order.service.domain.shell.port.output.publisher.AbstractOrderCompletedEventPublisher;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderCompleteOutboxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderCompletedOutboxScheduler extends AbstractOutboxSchedulerProcessor<OrderCompleteOutboxRepository, AbstractOrderCompletedEventPublisher, OrderCompletedEventOutboxMessage> {


    public OrderCompletedOutboxScheduler(OrderCompleteOutboxRepository outboxRepository, AbstractOrderCompletedEventPublisher eventPublisher) {
        super(outboxRepository, eventPublisher);
    }
}
