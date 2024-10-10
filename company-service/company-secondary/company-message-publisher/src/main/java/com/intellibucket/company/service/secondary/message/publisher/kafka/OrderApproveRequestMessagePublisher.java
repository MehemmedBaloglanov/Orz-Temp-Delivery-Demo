package com.intellibucket.company.service.secondary.message.publisher.kafka;

import com.intellibucket.company.service.domain.shell.outbox.model.OutboxMessage;
import com.intellibucket.company.service.domain.shell.outbox.model.payload.ProductApprovePayload;
import com.intellibucket.company.service.domain.shell.port.output.publisher.AbstractOrderApproveRequestMessagePublisher;
import com.intellibucket.company.service.secondary.message.publisher.helper.CompanyMessagePublisherHelper;
import com.intellibucket.company.service.secondary.message.publisher.mapper.CompanyMessagePublisherDataMapper;
import com.intellibucket.domain.exception.DomainException;
import com.intellibucket.kafka.config.producer.KafkaMessageHelper;
import com.intellibucket.kafka.config.producer.KafkaProducer;
import com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveRequestAvroModel;
import com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveResponseAvroModel;
import com.intellibucket.outbox.OutboxStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiConsumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderApproveRequestMessagePublisher implements AbstractOrderApproveRequestMessagePublisher {

    private final CompanyMessagePublisherDataMapper companyMessagePublisherDataMapper;
    private final KafkaProducer<String, CompanyOrderApproveResponseAvroModel> kafkaProducer;
    private final CompanyMessagePublisherHelper companyMessagePublisherHelper;
    private final KafkaMessageHelper kafkaMessageHelper;

    @Override
    public void publish(OutboxMessage message, BiConsumer<OutboxMessage, OutboxStatus> outboxCallback) throws DomainException {
        ProductApprovePayload payload = kafkaMessageHelper.getOrderEventPayload(message.getPayload(), ProductApprovePayload.class);

        log.info("Received OrderApproveRequestMessagePublisher for order id: {}", payload.getOrderId());
        try {
            CompanyOrderApproveResponseAvroModel avroModel = companyMessagePublisherDataMapper.productApprovePayloadToCompanyOrderApproveRequestAvroModel(payload);

            kafkaProducer.send(
                    "company-order-approve-response",
                    UUID.randomUUID().toString(),
                    avroModel,
                    companyMessagePublisherHelper.getCallback(avroModel, message, payload.getOrderId(), outboxCallback));

            log.info("OrderApproveRequestMessagePublisher sent to Kafka for order id: {}", payload.getOrderId());
        } catch (Exception e) {
            log.error("Error while sending OrderApproveRequestMessagePublisher to kafka with order id: {}, error: {}", payload.getOrderId(), e.getMessage());
        }
    }
}
