package com.intellibucket.company.service.domain.shell.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    private String id;
    private String name;
    private String description;
    private String status;
    private String address;
}

