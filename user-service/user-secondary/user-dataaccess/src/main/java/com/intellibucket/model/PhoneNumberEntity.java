package com.intellibucket.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long phoneNumberId;

    @Enumerated(EnumType.STRING)
    PhoneNumberType type;

    String countryCode;

    String number;
}