package com.intellibucket.order.service.primary.rest.command;


import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
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


    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany() throws CompanyDomainException {
        CompanyResponse companyResponse =  companyCommandServiceAdapter.createCompany();
        return new ResponseEntity<>(companyResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id) throws CompanyDomainException {
        companyCommandServiceAdapter.deleteCompany(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(@PathVariable Long id) throws CompanyDomainException {
        CompanyResponse companyResponse = companyCommandServiceAdapter.updateCompany(id);
        return new ResponseEntity<>(companyResponse, HttpStatus.OK);
    }
}
