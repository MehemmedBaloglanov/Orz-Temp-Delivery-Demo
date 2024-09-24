package com.intellibucket.order.service.primary.rest.command;


import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.rest.command.CompanyDeleteCommand;
import com.intellibucket.company.service.domain.shell.dto.rest.response.CompanyResponse;
import com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.command.CompanyCommandServiceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/1.0/company")
@RequiredArgsConstructor
public class CompanyCommandController {

    private final CompanyCommandServiceAdapter companyCommandServiceAdapter;


    //todo silinecek
    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany() throws CompanyDomainException {
        CompanyResponse companyResponse =  companyCommandServiceAdapter.createCompany();
        return new ResponseEntity<>(companyResponse, HttpStatus.CREATED);
    }

    //todo silinecek
    @DeleteMapping("/{id}")
    public void deleteCompany(@RequestBody CompanyDeleteCommand command) throws CompanyDomainException {
        companyCommandServiceAdapter.deleteCompany(command);
    }

    @PatchMapping()
    public void changeStatusToSuspended(@PathVariable CompanyDeleteCommand command) throws CompanyDomainException {
        companyCommandServiceAdapter.changeStatusToSuspend(command);
    }

    //todo silinecek
    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(@PathVariable Long id) throws CompanyDomainException {
        CompanyResponse companyResponse = companyCommandServiceAdapter.updateCompany(id);
        return new ResponseEntity<>(companyResponse, HttpStatus.OK);
    }



   //todo 1)changeSuspended 2)changeActive 3)updateBalance

}
