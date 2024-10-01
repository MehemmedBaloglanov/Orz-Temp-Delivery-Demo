package com.intellibucket.order.service.primary.rest.command;


import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.core.valueobject.CompanyAddress;
import com.intellibucket.company.service.domain.shell.dto.rest.command.*;
import com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.command.CompanyCommandServiceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/1.0/company")
@RequiredArgsConstructor
public class CompanyCommandController {

    private final CompanyCommandServiceAdapter companyCommandServiceAdapter;

    //SUSPENDED
    @PutMapping("/suspend")
    public void changeStatusToSuspended(@RequestBody CompanySuspendedCommand command) throws CompanyDomainException {
        companyCommandServiceAdapter.changeStatusToSuspend(command);
    }

    //ACTIVE
    @PutMapping("/active")
    public void changeCompanyStatusToActive(@RequestBody CompanyActiveCommand command) throws CompanyDomainException {
        companyCommandServiceAdapter.changeStatusToActive(command);
    }

    //DELETE
    @PutMapping("/delete")
    public void changeCompanyStatusToDelete(@RequestBody CompanyDeleteCommand command) throws CompanyDomainException {
        companyCommandServiceAdapter.changeStatusToDelete(command);
    }

}
