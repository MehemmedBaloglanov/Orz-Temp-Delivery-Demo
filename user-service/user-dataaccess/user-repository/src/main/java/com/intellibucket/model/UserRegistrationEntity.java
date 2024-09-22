package com.intellibucket.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intelliacademy.orizonroute.valueobjects.user.EmailType;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
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
public class UserRegistrationEntity {
    @Id
    @JsonProperty("id")
    UUID userEntityId;

    @NotNull
    @Email
    String email;

    @NotEmpty
    @NotNull
    @Size(min = 8, max = 25)
    @Pattern(regexp = Password.PATTERN)
    String password;

    @NotEmpty
    @NotNull
    private String oldPassword;

    @NotNull
    @Valid
    String phoneNumber;

    @Enumerated(EnumType.STRING)
    Status status;

    @Enumerated(EnumType.STRING)
    RoleAuthorithy roleAuthority;

    @Enumerated(EnumType.STRING)
    EmailType emailType;

    @OneToOne
    @JoinColumn(name = "user_address_id") // foreign key
    UserAddressEntity address;

    @OneToOne
    @JoinColumn(name = "phone_number_id") // foreign key
    PhoneNumberEntity phoneNumberEntity;
}