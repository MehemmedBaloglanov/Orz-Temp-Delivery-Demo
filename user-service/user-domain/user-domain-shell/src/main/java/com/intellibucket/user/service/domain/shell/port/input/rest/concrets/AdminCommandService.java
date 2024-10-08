package com.intellibucket.user.service.domain.shell.port.input.rest.concrets;

import com.intellibucket.repository.RoleRepository;
import com.intellibucket.repository.StatusRepository;
import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.core.valueObject.Status;
import com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.AbstractAdminCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCommandService implements AbstractAdminCommandService {
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
    public List<Status> getAllActiveCustomers() {
        return statusRepository.findByStatusAndRoleAuthority(Status.ACTIVE, RoleAuthorithy.CUSTOMER);
    }

    @Override
    public List<Status> getAllActiveCompanies() {
        return statusRepository.findByStatusAndRoleAuthority(Status.ACTIVE, RoleAuthorithy.COMPANY);
    }
}