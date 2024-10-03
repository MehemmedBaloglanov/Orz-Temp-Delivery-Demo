package com.intellibucket.company.service.domain.shell.port.output.publisher;

import com.intellibucket.company.service.domain.shell.outbox.model.OutboxMessage;
import com.intellibucket.domain.message.publisher.BaseMessagePublisher;

public interface AbstractOrderApproveRequestMessagePublisher extends BaseMessagePublisher<OutboxMessage> {

}
