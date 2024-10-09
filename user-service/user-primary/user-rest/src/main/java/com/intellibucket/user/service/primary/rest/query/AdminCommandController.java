package com.intellibucket.user.service.primary.rest.query;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.core.valueObject.Status;
import com.intellibucket.user.service.domain.shell.dto.query.FetchUserAddressByIdCommand;
import com.intellibucket.user.service.domain.shell.dto.query.FetchUserByIdCommand;
import com.intellibucket.user.service.domain.shell.dto.query.FetchUsersByStatusAndRoleCommand;
import com.intellibucket.user.service.domain.shell.dto.response.UserAddressResponse;
import com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.query.UserQueryServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/1.0/admin")
public class AdminCommandController {
    private final UserQueryServicePort adminCommandService;

    @GetMapping("/fetchUserById/{id}")
    public Optional<UserRoot> fetchUserById(@PathVariable("id") UUID userId) throws UserNotFoundException {
        UserID userIdObject = UserID.of(userId);
        FetchUserByIdCommand command = new FetchUserByIdCommand(userIdObject);
        return adminCommandService.findByUserId(command);
    }

    @GetMapping("/fetchUserByStatusAndRole")
    public List<UserRoot> fetchUserByStatusAndRole(@RequestParam("role") RoleAuthorithy roleAuthorithy, @RequestParam("status") Status status) {
        FetchUsersByStatusAndRoleCommand command = new FetchUsersByStatusAndRoleCommand(roleAuthorithy.name(), status.name());
        return adminCommandService.getUsersByStatusAndByRole(command);
    }

    @GetMapping("/fetchUserAddressById/{id}")
    public Optional<UserAddressResponse> fetchUserAddressById(@PathVariable("id") UUID userId) throws UserNotFoundException {
        UserID userIdObject = UserID.of(userId);
        FetchUserAddressByIdCommand command = new FetchUserAddressByIdCommand(userIdObject);
        return adminCommandService.findAddressByUserId(command);
    }
}