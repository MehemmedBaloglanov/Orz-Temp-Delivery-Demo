package com.intellibucket.order.service.primary.rest.command;


import com.intellibucket.company.service.domain.shell.dto.rest.response.CompanyResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1.0/company")
public class CompanyCommandController {

    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany(){
        return null;
    }


}
