package com.intellibucket.user.service.primary.rest.query;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.core.valueObject.Status;
import com.intellibucket.user.service.domain.shell.dto.query.FetchUserByIdCommand;
import com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.query.UserQueryServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminCommandController {
    private final UserQueryServicePort adminCommandService;

    @GetMapping("/fetchUserById/{id}")
    public Optional<UserRoot> fetchUserById(@PathVariable("id") UUID userId) {
        UserID userIdObject = UserID.of(userId);
        FetchUserByIdCommand command = new FetchUserByIdCommand(userIdObject);
        return adminCommandService.findByUserId(command);
    }

    @GetMapping("/fetchUserByStatusAndRole/{role}/{status}")
    public List<UserRoot> fetchUserByStatusAndRole(@PathVariable("role") RoleAuthorithy roleAuthorithy, @PathVariable("status") Status status) {
//        FetchUsersByRoleAndAuthorityCommand command = new FetchUsersByRoleAndAuthorityCommand();
        return null; //adminCommandService.getUsersByStatusAndByRole(command);
    }
}