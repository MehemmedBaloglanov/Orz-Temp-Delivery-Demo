package com.intellibucket.user.service.repository.model;

import com.intelliacademy.orizonroute.valueobjects.user.EmailType;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.core.valueObject.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
@SuperBuilder
@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseUserEntity {
    @Id
    @Column(name = "id")

    UUID userEntityId;

    String email;

    @NotEmpty
    @NotNull
    @Size(min = 8, max = 25)
    @Pattern(regexp = Password.PATTERN)
     String password;

    @Enumerated(EnumType.STRING)
    Status status;

    @Enumerated(EnumType.STRING)
    RoleAuthorithy roleAuthority;

    @Enumerated(EnumType.STRING)
    EmailType emailType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_address_id")
    UserAddressEntity address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phone_number_id")
    PhoneNumberEntity phoneNumberEntity;
}