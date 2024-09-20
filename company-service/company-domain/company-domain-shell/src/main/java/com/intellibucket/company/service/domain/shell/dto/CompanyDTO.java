package com.intellibucket.company.service.domain.shell.dto;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    private CompanyID id;
    private String name;
    private String description;
    private String status;
    private String address;
}

