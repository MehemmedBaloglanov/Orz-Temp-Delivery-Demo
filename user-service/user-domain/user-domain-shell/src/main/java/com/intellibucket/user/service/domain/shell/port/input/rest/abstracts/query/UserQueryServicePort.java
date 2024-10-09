package com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.query;

import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.shell.dto.query.FetchUserAddressByIdCommand;
import com.intellibucket.user.service.domain.shell.dto.query.FetchUserByIdCommand;
import com.intellibucket.user.service.domain.shell.dto.query.FetchUsersByStatusAndRoleCommand;
import com.intellibucket.user.service.domain.shell.dto.response.UserAddressResponse;

import java.util.List;
import java.util.Optional;

public interface UserQueryServicePort {
    List<UserRoot> getUsersByStatusAndByRole(FetchUsersByStatusAndRoleCommand command);

    Optional<UserRoot> findByUserId(FetchUserByIdCommand command) throws UserNotFoundException;

    Optional<UserAddressResponse> findAddressByUserId(FetchUserAddressByIdCommand command) throws UserNotFoundException;
}