package com.intellibucket.order.service.primary.rest.query;

import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.shell.dto.rest.response.CompanyResponse;
import com.intellibucket.company.service.domain.shell.port.input.rest.abstracts.query.CompanyQueryServiceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1.0/company")
@RequiredArgsConstructor
public class CompanyQueryController {

    private final CompanyQueryServiceAdapter companyQueryServiceAdapter;

    //GET COMPANY WITH ID
    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable Long id) throws CompanyDomainException {
        CompanyResponse companyResponse = companyQueryServiceAdapter.getCompanyById(id);
        return new ResponseEntity<>(companyResponse, HttpStatus.OK);
    }

    //All company
}
