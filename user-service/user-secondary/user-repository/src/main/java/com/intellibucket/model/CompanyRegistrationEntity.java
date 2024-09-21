package com.intellibucket.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intellibucket.user.service.domain.core.valueObject.Status;
import jakarta.persistence.*;
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
public class CompanyRegistrationEntity {
    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID companyEntityId;

    @NotEmpty
    @NotNull
    @Size(min = 4, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9_]*$")
    String companyName;

    @NotEmpty
    @NotNull
    String companyLicense;

    @Enumerated(EnumType.STRING)
    private Status status;
}