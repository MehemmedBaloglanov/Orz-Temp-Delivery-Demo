package com.intellibucket.order.service.domain.shell.outbox.scheduler.processor;

import com.intellibucket.outbox.OutboxStatus;
import com.intellibucket.saga.SagaStatus;
import com.intellibucket.order.service.domain.shell.outbox.model.message.OrderPaymentOutboxMessage;
import com.intellibucket.order.service.domain.shell.outbox.scheduler.abstracts.AbstractOutboxSchedulerProcessor;
import com.intellibucket.order.service.domain.shell.port.output.publisher.AbstractPaymentRequestMessagePublisher;
import com.intellibucket.order.service.domain.shell.port.output.repository.OrderPaymentOutboxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.intellibucket.saga.order.SagaConstants.ORDER_SAGA_NAME;

@Slf4j
@Component
public class OrderPaymentOutboxScheduler extends AbstractOutboxSchedulerProcessor<OrderPaymentOutboxRepository, AbstractPaymentRequestMessagePublisher, OrderPaymentOutboxMessage> {


    public OrderPaymentOutboxScheduler(OrderPaymentOutboxRepository outboxRepository, AbstractPaymentRequestMessagePublisher eventPublisher) {
        super(outboxRepository, eventPublisher);
    }

    @Override
    @Transactional
    @Scheduled(fixedDelayString = "${order-service.outbox-scheduler-fixed-rate}", initialDelayString = "${order-service.outbox-scheduler-initial-delay}")
    public void processOutboxMessage() {

        SagaStatus[] sagaStatuses = new SagaStatus[]{SagaStatus.STARTED, SagaStatus.COMPENSATING};

        Optional<List<OrderPaymentOutboxMessage>> outboxMessagesResponse = outboxRepository.findByTypeAndOutboxStatusAndSagaStatus(ORDER_SAGA_NAME, OutboxStatus.STARTED, sagaStatuses);

        if (outboxMessagesResponse.isPresent() && !outboxMessagesResponse.get().isEmpty()) {
            List<OrderPaymentOutboxMessage> outboxMessages = outboxMessagesResponse.get();
            log.info("Received {} OrderPaymentOutboxMessage with ids: {}, sending to message bus!",
                    outboxMessages.size(),
                    outboxMessages.stream().map(outboxMessage -> outboxMessage.getId().toString()).collect(Collectors.joining(",")));

            outboxMessages.forEach(outboxMessage -> eventPublisher.publish(outboxMessage, this::updateOutboxStatus));
            log.info("{} OrderPaymentOutboxMessage sent to message bus!", outboxMessages.size());
        }

    }
}
