package com.intellibucket.order.service.secondary.message.publisher.helper;

import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intellibucket.message.model.BaseMessageModel;
import com.intellibucket.order.service.domain.shell.outbox.model.OutboxMessage;
import com.intellibucket.order.service.domain.shell.outbox.model.payload.BaseEventPayload;
import com.intellibucket.outbox.OutboxStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Slf4j
@Component
public class OrderKafkaPublisherHelper {


    public <T extends SpecificRecordBase, P extends BaseEventPayload> BiConsumer<SendResult<String, T>, Throwable>
    getCallback(T avroModel, OutboxMessage message, String orderId, BiConsumer<OutboxMessage, OutboxStatus> outboxCallback) {
        return (result, ex) -> {
            if (ex != null) {
                log.error("Error while sending {} with message: {}, exception: {}",
                        avroModel.getClass().getSimpleName(),
                        avroModel, message.getClass().getSimpleName(),
                        ex);
                outboxCallback.accept(message, OutboxStatus.FAILED);
            } else {
                RecordMetadata recordMetadata = result.getRecordMetadata();
                log.info("Received successful response from Kafka for order id: {} Topic: {} Partition: {} Offset: {} Timestamp: {}",
                        orderId,
                        recordMetadata.offset(),
                        recordMetadata.partition(),
                        recordMetadata.topic(),
                        recordMetadata.timestamp()
                );
                outboxCallback.accept(message, OutboxStatus.COMPLETED);
            }
        };
    }
}
