package com.intellibucket.user.service.configuration.kafka;

import com.intellibucket.user.service.domain.core.event.UserDomainEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @Bean
    public ProducerFactory<String, UserDomainEvent> producerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put("bootstrap.servers", "localhost:9092"); // Adjust as necessary
        config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("value.serializer", "org.springframework.kafka.support.serializer.ErrorHandlingSerializer");
        config.put("value.deserializer", "org.springframework.kafka.support.serializer.ErrorHandlingDeserializer");
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, UserDomainEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}