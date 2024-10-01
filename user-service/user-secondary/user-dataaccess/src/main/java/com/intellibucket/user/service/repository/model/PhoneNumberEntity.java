package com.intellibucket.user.service.repository.model;

import com.intelliacademy.orizonroute.valueobjects.user.PhoneNumberType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhoneNumberEntity {
    @Id
    @Column(name = "id")
    UUID phoneNumberId;

    @Enumerated(EnumType.STRING)
    PhoneNumberType type;

    String countryCode;

    String number;
}