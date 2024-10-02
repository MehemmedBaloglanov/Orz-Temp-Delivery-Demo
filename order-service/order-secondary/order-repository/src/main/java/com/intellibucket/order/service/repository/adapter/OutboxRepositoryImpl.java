package com.intellibucket.order.service.repository.adapter;

import com.intellibucket.order.service.domain.core.exception.OrderDomainException;
import com.intellibucket.order.service.domain.shell.outbox.model.OutboxMessage;
import com.intellibucket.order.service.domain.shell.port.output.repository.OutboxRepository;
import com.intellibucket.order.service.repository.helper.OrderRepositoryDataHelper;
import com.intellibucket.order.service.repository.mapper.OrderDataAccessMapper;
import com.intellibucket.order.service.repository.model.outbox.OutboxJpaEntity;
import com.intellibucket.order.service.repository.model.outbox.OutboxJpaStatus;
import com.intellibucket.order.service.repository.repository.OrderOutboxRepository;
import com.intellibucket.outbox.OutboxStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OutboxRepositoryImpl implements OutboxRepository {
    private final OrderOutboxRepository outboxRepository;
    private final OrderDataAccessMapper orderDataAccessMapper;
    private final OrderRepositoryDataHelper orderRepositoryDataHelper;

    @Override
    public Optional<List<OutboxMessage>> findByOutboxStatus(OutboxStatus outboxStatus) throws OrderDomainException {
        OutboxJpaStatus outboxJpaStatus = orderRepositoryDataHelper.outboxStatusToOutboxJpaStatus(outboxStatus);
        Optional<List<OutboxJpaEntity>> outboxJpaEntityOptional = outboxRepository.findByOutboxStatus(outboxJpaStatus);
        if (outboxJpaEntityOptional.isPresent()) {
            List<OutboxJpaEntity> outboxJpaEntityList = outboxJpaEntityOptional.get();
            List<OutboxMessage> outboxMessages = new ArrayList<>();

            for (OutboxJpaEntity item : outboxJpaEntityList) {
                OutboxMessage outboxMessage = orderDataAccessMapper.outboxJpaEntityToOutboxMessage(item);
                outboxMessages.add(outboxMessage);
            }

            return Optional.of(outboxMessages);

        } else {
            return Optional.empty();
        }
    }

    @Override
    public OutboxMessage save(OutboxMessage outboxMessages) throws OrderDomainException {
        OutboxJpaEntity outboxJpaEntity = orderDataAccessMapper.outboxMessageToOutboxJpaEntity(outboxMessages);
        OutboxJpaEntity saveEntity = outboxRepository.save(outboxJpaEntity);
        return orderDataAccessMapper.outboxJpaEntityToOutboxMessage(saveEntity);
    }

    @Override
    public void deleteByOutboxStatus(OutboxStatus outboxStatus) throws OrderDomainException {
        outboxRepository.deleteByOutboxStatus(orderRepositoryDataHelper.outboxStatusToOutboxJpaStatus(outboxStatus));
    }
}
