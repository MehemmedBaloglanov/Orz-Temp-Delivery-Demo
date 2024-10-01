package com.intellibucket.order.service.domain.shell.outbox.model.message;

import com.intellibucket.message.model.BaseMessageModel;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class OrderCompletedEventOutboxMessage extends BaseMessageModel {

}
