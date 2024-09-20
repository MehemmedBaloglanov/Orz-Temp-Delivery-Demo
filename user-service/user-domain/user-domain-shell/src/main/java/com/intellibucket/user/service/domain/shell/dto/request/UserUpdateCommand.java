package com.intellibucket.user.service.domain.shell.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateCommand {
    private String userId;
    private String lastName;
    private String firstName;
    private String email;
    private String phoneNumber;
}
