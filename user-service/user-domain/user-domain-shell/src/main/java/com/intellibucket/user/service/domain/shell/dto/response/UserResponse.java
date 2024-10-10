package com.intellibucket.user.service.domain.shell.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@Builder
@AllArgsConstructor

public class UserResponse {

    @JsonProperty("email")
    private final String email;

    @JsonProperty("roleAuthority")
    private final String roleAuthorithy;

    @JsonProperty("address")
    private final String address;

    @JsonProperty("phoneNumber")
    private final String phoneNumber;

    @JsonProperty("is_active")
    private final boolean isActive;

    @JsonProperty("created_at")
    private final OffsetDateTime createdAt;


}


