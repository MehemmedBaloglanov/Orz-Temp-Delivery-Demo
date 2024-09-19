package com.intellibucket.user.service.domain.shell.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intellibucket.user.service.domain.core.valueObject.Address;
import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor

public class UserResponse {
    @Id
    private final UUID id;

//    @JsonProperty("password")
//    private final Password password;

    @JsonProperty("email")
    private final Email email;

    @JsonProperty("roleAuthority")
    private final RoleAuthorithy roleAuthorithy;

    @JsonProperty("address")
    private final Address address;

    @JsonProperty("phoneNmber")
    private final String phoneNumber;

    @JsonProperty("is_active")
    private final boolean isActive;

    @JsonProperty("created_at")
    private final OffsetDateTime createdAt;


}


