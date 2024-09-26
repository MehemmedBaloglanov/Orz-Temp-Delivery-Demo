package com.intellibucket.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import com.intellibucket.user.service.domain.core.valueObject.Status;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRegistrationEntity {
    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID customerEntityId;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 25)
    @Pattern(regexp = "^[a-zA-Z]+$")
    String userFirstName;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 25)
    @Pattern(regexp = "^[a-zA-Z]+$")
    String userLastName;

    @NotNull
    @Email
    String email;

    @NotEmpty
    @NotNull
    @Size(min = 8, max = 25)
    @Pattern(regexp = Password.PATTERN)
    String password;

    @NotNull
    String birthDate;

    @NotNull
    @Valid
    String phoneNumber;

    @NotNull
    String gender;

    @Enumerated(EnumType.STRING)
    Status status;
}