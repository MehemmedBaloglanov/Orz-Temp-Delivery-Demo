package com.intellibucket.user.service.domain.shell.handler.query;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.shell.dto.query.FetchUserByIdCommand;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FetchByIdQuery {
    private final UserRepository userRepository;

    public Optional<UserRoot> handle(FetchUserByIdCommand command) {

        UserID userId = command.getUserId();

        return userRepository.findByUserId(userId);
    }
}