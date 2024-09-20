package com.intellibucket.company.service.company.repository.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Builder
public class CompanyJpaAddress {
    private String street;

    private String city;

    private String state;
}
