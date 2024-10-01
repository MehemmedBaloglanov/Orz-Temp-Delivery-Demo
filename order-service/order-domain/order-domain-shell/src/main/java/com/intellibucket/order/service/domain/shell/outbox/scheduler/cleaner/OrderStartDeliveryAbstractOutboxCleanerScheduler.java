package com.intellibucket.order.service.domain.shell.outbox.scheduler.cleaner;

import com.intellibucket.order.service.domain.shell.outbox.model.message.OrderStartDeliveryEventOutboxMessage;
import com.intellibucket.order.service.domain.shell.outbox.scheduler.abstracts.AbstractOutboxCleanerScheduler;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderStartDeliveryOutboxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderStartDeliveryAbstractOutboxCleanerScheduler extends AbstractOutboxCleanerScheduler<OrderStartDeliveryOutboxRepository, OrderStartDeliveryEventOutboxMessage> {


    public OrderStartDeliveryAbstractOutboxCleanerScheduler(OrderStartDeliveryOutboxRepository outboxRepository) {
        super(outboxRepository);
    }

}
