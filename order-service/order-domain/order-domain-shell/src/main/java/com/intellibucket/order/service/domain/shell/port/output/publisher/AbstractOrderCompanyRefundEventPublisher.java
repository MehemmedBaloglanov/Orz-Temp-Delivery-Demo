package com.intellibucket.order.service.domain.shell.port.output.publisher;

import com.intellibucket.domain.message.publisher.BaseMessagePublisher;
import com.intellibucket.order.service.domain.shell.outbox.model.OutboxMessage;

public interface AbstractOrderCompanyRefundEventPublisher extends BaseMessagePublisher<OutboxMessage> {
}
