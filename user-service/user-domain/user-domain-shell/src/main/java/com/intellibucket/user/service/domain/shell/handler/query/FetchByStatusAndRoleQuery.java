package com.intellibucket.user.service.domain.shell.handler.query;

import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.core.valueObject.Status;
import com.intellibucket.user.service.domain.shell.dto.query.FetchUsersByStatusAndRoleCommand;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FetchByStatusAndRoleQuery {
    private final UserRepository userRepository;

    public List<UserRoot> handle(FetchUsersByStatusAndRoleCommand command) {
        RoleAuthorithy roleAuthority = RoleAuthorithy.valueOf(command.getRoleAuthority());
        Status status = Status.valueOf(command.getStatus());
        return userRepository.findByStatusAndRoleAuthority(status, roleAuthority);
    }
}