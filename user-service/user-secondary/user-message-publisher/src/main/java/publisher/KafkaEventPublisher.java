package publisher;

import com.intellibucket.user.service.domain.core.event.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventPublisher {
    private final KafkaTemplate<String, UserDomainEvent> kafkaTemplate;

    public KafkaEventPublisher(KafkaTemplate<String, UserDomainEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishUserChangePasswordEvent(UserChangePasswordDomainEvent event) {
        kafkaTemplate.send("user-change-password-topic", event);
    }

    public void publishUserDeletedEvent(UserDeletedDomainEvent event) {
        kafkaTemplate.send("user-deleted-topic", event);
    }

    public void publishUserLoggedInEvent(UserLoggedInDomainEvent event) {
        kafkaTemplate.send("user-logged-in-topic", event);
    }

    public void publishUserRegisteredEvent(UserRegisteredEvent event) {
        kafkaTemplate.send("user-registered-topic", event);
    }

    public void publishUserUpdatedEvent(UserUpdatedDomainEvent event) {
        kafkaTemplate.send("user-updated-topic", event);
    }
}