package com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.query;

import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.shell.dto.query.FetchUserByIdCommand;

import java.util.Optional;

public interface UserQueryServicePort {
//    List<UserRoot> getUsersByStatusAndByRole(FetchUsersByRoleAndAuthorityCommand command);

    Optional<UserRoot> findByUserId(FetchUserByIdCommand command);
}