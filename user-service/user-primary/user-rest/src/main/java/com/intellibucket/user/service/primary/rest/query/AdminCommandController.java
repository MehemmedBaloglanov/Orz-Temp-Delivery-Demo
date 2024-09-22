package com.intellibucket.user.service.primary.rest.query;

import com.intellibucket.model.CompanyRegistrationEntity;
import com.intellibucket.model.CustomerRegistrationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminCommandController {
    @GetMapping("/allCustomers")
    public List<CustomerRegistrationEntity> getAllCustomers() {
        return null;
    }

    @GetMapping("/allCompanies")
    public List<CompanyRegistrationEntity> getAllCompany() {
        return null;
    }
}