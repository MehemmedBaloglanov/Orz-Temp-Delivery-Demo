package com.intellibucket.model;

import com.intelliacademy.orizonroute.valueobjects.user.EmailType;
import com.intellibucket.user.service.domain.core.valueObject.Password;
import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.core.valueObject.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_address_id") // foreign key
    UserAddressEntity address;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phone_number_id") // foreign key
    PhoneNumberEntity phoneNumberEntity;
}