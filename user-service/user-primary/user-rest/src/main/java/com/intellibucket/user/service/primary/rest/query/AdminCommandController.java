package com.intellibucket.user.service.primary.rest.query;

import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.core.valueObject.Status;
import com.intellibucket.user.service.domain.shell.port.input.rest.concrets.AdminCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminCommandController {
    private final AdminCommandService adminCommandService;

    @GetMapping("/allCustomers")
    public List<RoleAuthorithy> getAllCustomers() {
        return adminCommandService.getAllCustomers();
    }

    @GetMapping("/allCompanies")
    public List<RoleAuthorithy> getAllCompanies() {
        return adminCommandService.getAllCompanies();
    }

    @GetMapping("/allDeactiveCustomers/{status}/{role}")
    public List<Status> getUsersByStatusAndByRole(@PathVariable Status status, @PathVariable RoleAuthorithy role) {
        return adminCommandService.getUsersByStatusAndByRole(status, role);
    }
}