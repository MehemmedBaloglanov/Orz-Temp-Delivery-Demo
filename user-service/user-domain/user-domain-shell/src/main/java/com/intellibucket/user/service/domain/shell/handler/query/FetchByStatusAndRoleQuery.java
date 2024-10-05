package com.intellibucket.user.service.domain.shell.handler.query;

import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.shell.dto.query.FetchUsersByRoleAndAuthorityCommand;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FetchByStatusAndRoleQuery {
    private final UserRepository userRepository;

    public List<UserRoot> handle(FetchUsersByRoleAndAuthorityCommand command) {
//        RoleAuthorithy roleAuthority = command.getRoleAuthority();
//        Status status = command.getStatus();

        // Fetch users based on role and status
//        List<UserRoot> users = userRepository.fetchUsersByRoleAndStatus(roleAuthority, status);

        return null;// userRepository.fetchUsersByRoleAndStatus(roleAuthority, status);
    }
}