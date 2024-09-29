package com.intellibucket.user.service.primary.rest.query;

import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.core.valueObject.Status;
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
//    private final UserQueryService adminCommandService;

    @GetMapping("/allCustomers")
    public List<RoleAuthorithy> getAllCustomers() {
        return null;
    }

    @GetMapping("/allCompanies")
    public List<RoleAuthorithy> getAllCompanies() {
        return null;
    }

    @GetMapping("/getUsersByStatusAndByRole/{status}/{role}")
    public List<Status> getUsersByStatusAndByRole(@PathVariable Status status, @PathVariable RoleAuthorithy role) {
        return null;
    }
}