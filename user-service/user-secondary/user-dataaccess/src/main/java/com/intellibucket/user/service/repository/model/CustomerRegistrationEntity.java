package com.intellibucket.user.service.repository.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class CustomerRegistrationEntity extends BaseUserEntity {
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 25)
    private String username;
}