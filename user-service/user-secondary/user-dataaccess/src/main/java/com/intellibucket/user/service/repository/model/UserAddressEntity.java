package com.intellibucket.user.service.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAddressEntity {
    @Id
    @Column(name = "id")
    UUID userAddressId;
    String street;
    String city;
    String state;
    String postalCode;
    String country;
}