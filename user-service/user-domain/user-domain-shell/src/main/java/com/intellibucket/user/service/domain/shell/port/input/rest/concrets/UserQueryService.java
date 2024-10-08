package com.intellibucket.user.service.domain.shell.port.input.rest.concrets;

import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.shell.dto.query.FetchUserByIdCommand;
import com.intellibucket.user.service.domain.shell.dto.query.FetchUsersByStatusAndRoleCommand;
import com.intellibucket.user.service.domain.shell.handler.query.FetchByIdQuery;
import com.intellibucket.user.service.domain.shell.handler.query.FetchByStatusAndRoleQuery;
import com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.query.UserQueryServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserQueryService implements UserQueryServicePort {
    private final FetchByStatusAndRoleQuery fetchByStatusAndRoleQuery;
    private final FetchByIdQuery fetchByIdQuery;

    @Override
    public List<UserRoot> getUsersByStatusAndByRole(FetchUsersByStatusAndRoleCommand command) {
        return fetchByStatusAndRoleQuery.handle(command);
    }

    @Override
    public Optional<UserRoot> findByUserId(FetchUserByIdCommand command) {
        return fetchByIdQuery.handle(command);
    }
}