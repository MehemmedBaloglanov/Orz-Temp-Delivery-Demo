package service;

import com.intellibucket.user.service.domain.core.event.UserDomainEvent;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class UserEventListener {
    @KafkaListener(topics = "topic", groupId = "group-id")
    public void listen(UserDomainEvent event) {
        UserRoot userRoot = event.getUserRoot();

        OffsetDateTime createdAt = event.getCreatedAt();

        System.out.println("Received event is: " + userRoot + " at " + createdAt);
    }
}