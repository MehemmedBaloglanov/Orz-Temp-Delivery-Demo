package com.intellibucket.user.service.domain.shell.outbox.model.message;

import com.intellibucket.message.model.BaseMessageModel;
import com.intellibucket.outbox.OutboxStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder
//@AllArgsConstructor
public class CompanyCreatedOutboxMessage extends BaseMessageModel {
    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public String getPayload() {
        return "";
    }

    @Override
    public void setOutboxStatus(OutboxStatus outboxStatus) {

    }
}
