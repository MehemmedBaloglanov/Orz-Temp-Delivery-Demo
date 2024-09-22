package com.intellibucket.user.service.domain.shell.handler2;

import com.intellibucket.model.UserRegistrationEntity;
import com.intellibucket.repository.UserRepository;
import com.intellibucket.user.service.domain.core.event.UserRegisteredEvent;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.core.valueObject.Status;
import com.intellibucket.user.service.domain.shell.message.UserEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

import static com.intellibucket.mapper.DataAccessMapper.toUserRegistrationEntity;
@Component
@RequiredArgsConstructor
public class CompanyRegisterServiceHandler {
    private final UserRepository userRepository;
    private final UserEventPublisher userEventPublisher;

    public void registerAsCompany(UserRoot userRoot, String firstName, String lastName) {
        UserRegistrationEntity userRegistrationEntity = toUserRegistrationEntity(userRoot, firstName, lastName);
        userRoot.setStatus(Status.ACTIVE);
        userRoot.setRoleAuthorithy(RoleAuthorithy.COMPANY);

        userRepository.save(userRegistrationEntity);

        UserRegisteredEvent userRegisteredEvent = new UserRegisteredEvent(userRoot, OffsetDateTime.now());
        userEventPublisher.publish(userRegisteredEvent);
    }
}