package com.intellibucket.user.service.domain.shell.port.input.rest.concrets;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.core.valueObject.Status;
import com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.query.UserQueryServicePort;
import com.intellibucket.user.service.domain.shell.port.output.repository.RoleRepository;
import com.intellibucket.user.service.domain.shell.port.output.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserQueryService implements UserQueryServicePort {
    private final RoleRepository roleRepository;
    private final StatusRepository statusRepository;

    @Override
    public List<RoleAuthorithy> getAllCustomers() {
        return roleRepository.findByAuthority(RoleAuthorithy.CUSTOMER.name());
    }

    @Override
    public List<RoleAuthorithy> getAllCompanies() {
        return roleRepository.findByAuthority(RoleAuthorithy.COMPANY.name());
    }

    @Override
    public List<Status> getUsersByStatusAndByRole(Status status, RoleAuthorithy role) {
        return statusRepository.findByStatusAndRoleAuthority(status, role);
    }

    @Override
    public Optional<UserRoot> findByUserId(UserID userID) {
        return Optional.empty();
    }
}