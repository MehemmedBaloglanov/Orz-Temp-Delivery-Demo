package com.intellibucket.user.service.domain.shell.dto.response;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
