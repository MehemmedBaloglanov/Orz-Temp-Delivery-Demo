package com.intellibucket.user.service.domain.shell.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserRegistrationResponseInfo {
    private final UUID userId;
    private final UUID profileId;

    public static UserRegistrationResponseInfo of(UUID userId, UUID profileId) {
        return new UserRegistrationResponseInfo(userId, profileId);
    }
}
