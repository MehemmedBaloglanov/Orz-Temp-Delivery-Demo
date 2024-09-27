package com.intellibucket.company.service.message.listener.kafka;

import com.intellibucket.company.service.domain.shell.port.input.listener.ProductStockUpdateMessageListener;
import com.intellibucket.company.service.message.listener.mapper.CompanyMessagePublisherDataMapper;
import com.intellibucket.kafka.config.consumer.KafkaConsumer;
import com.intellibucket.kafka.order.avro.model.ProductStockUpdateRequestAvroModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductStockUpdateKafkaListener implements KafkaConsumer<ProductStockUpdateRequestAvroModel> {
    private final CompanyMessagePublisherDataMapper companyMessagePublisherDataMapper;
    private final ProductStockUpdateMessageListener productStockUpdateMessageListener;

    @Override
    @KafkaListener(groupId = "#product-service.product-service-group-id", topics = "#product-service.product-stock-update-topic")
    public void receive(
            @Payload List<ProductStockUpdateRequestAvroModel> messages,
            @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
            @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
            @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        messages.forEach(message -> {
            OrderProductsReponse orderProductsReponse = companyMessagePublisherDataMapper.productStockUpdateRequestAvroModelToOrderProductsResponse(message);
//            productStockUpdateMessageListener.
        });

    }
}
