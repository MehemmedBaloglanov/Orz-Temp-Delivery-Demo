package com.intellibucket.order.service.domain.shell.outbox.scheduler.processor;

import com.intellibucket.order.service.domain.shell.outbox.model.message.OrderStartDeliveryEventOutboxMessage;
import com.intellibucket.order.service.domain.shell.outbox.scheduler.abstracts.AbstractOutboxSchedulerProcessor;
import com.intellibucket.order.service.domain.shell.port.output.publisher.AbstractOrderStartDeliveryEventPublisher;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderStartDeliveryOutboxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderStartDeliveryOutboxScheduler extends AbstractOutboxSchedulerProcessor<OrderStartDeliveryOutboxRepository, AbstractOrderStartDeliveryEventPublisher, OrderStartDeliveryEventOutboxMessage> {


    public OrderStartDeliveryOutboxScheduler(OrderStartDeliveryOutboxRepository outboxRepository, AbstractOrderStartDeliveryEventPublisher eventPublisher) {
        super(outboxRepository, eventPublisher);
    }

}
