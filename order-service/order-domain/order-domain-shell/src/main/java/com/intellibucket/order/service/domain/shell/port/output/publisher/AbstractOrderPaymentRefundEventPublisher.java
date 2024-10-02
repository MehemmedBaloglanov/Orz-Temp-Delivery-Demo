package com.intellibucket.order.service.domain.shell.port.output.publisher;

import com.intellibucket.domain.message.publisher.BaseMessagePublisher;
import com.intellibucket.order.service.domain.shell.outbox.model.OutboxMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


public interface AbstractOrderPaymentRefundEventPublisher extends BaseMessagePublisher<OutboxMessage> {
}
