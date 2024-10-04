package com.intellibucket.mock.company.service.consumer;

import com.intellibucket.kafka.config.consumer.KafkaConsumer;
import com.intellibucket.kafka.order.avro.model.company.ApproveAvroStatus;
import com.intellibucket.kafka.order.avro.model.company.CompanyOrderApproveRequestAvroModel;
import com.intellibucket.mock.company.service.producer.ProductApproveKafkaPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductApproveKafkaListener implements KafkaConsumer<CompanyOrderApproveRequestAvroModel> {

    private final ProductApproveKafkaPublisher productApproveKafkaPublisher;
    private Random random = new Random();

    @Override
    @KafkaListener(groupId = "company-service-group", topics = "company-order-approve-request")
    public void receive(List<CompanyOrderApproveRequestAvroModel> messages, List<String> keys, List<Integer> partitions, List<Long> offsets) {
        log.info("{} number of CompanyOrderApproveResponseAvroModel received with keys:{}, partitions:{} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());
        messages.forEach(message -> {

            boolean nextBoolean = random.nextBoolean();
            productApproveKafkaPublisher.publish(message.getOrderId(), ApproveAvroStatus.REJECTED);
        });
    }
}
