package com.intellibucket.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserJpaAddress {
    private String street;
    private String city;
    private String state;


}
