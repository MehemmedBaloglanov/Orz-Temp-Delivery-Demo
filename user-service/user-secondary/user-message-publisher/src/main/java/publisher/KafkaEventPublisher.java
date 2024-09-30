package publisher;

import com.intellibucket.user.service.domain.core.event.UserDomainEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventPublisher {
    private final KafkaTemplate<String, UserDomainEvent> kafkaTemplate;

    public KafkaEventPublisher(KafkaTemplate<String, UserDomainEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishEvent(UserDomainEvent event) {
        kafkaTemplate.send("user-registered-topic", event);
    }
}