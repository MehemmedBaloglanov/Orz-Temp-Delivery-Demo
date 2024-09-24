package com.intellibucket.order.service.domain.shell.outbox.model.message;

import com.intellibucket.message.model.BaseMessageModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class OrderCompletedEventOutboxMessage implements BaseMessageModel {
}
