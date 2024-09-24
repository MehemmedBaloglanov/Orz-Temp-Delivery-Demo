package com.intellibucket.order.service.domain.shell.outbox.scheduler;

import com.food.ordering.system.outbox.OutboxScheduler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderStartDeliveryOutboxCleanerScheduler implements OutboxScheduler {

    @Override
    @Scheduled(cron = "@midnight")
    public void processOutboxMessage() {

    }
}
