package com.intellibucket.user.service.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "company")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyRegistrationEntity extends BaseUserEntity {
    @NotNull
    @NotEmpty
    @Size(min = 4, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9_]*$")
    String companyName;
    String username;
}