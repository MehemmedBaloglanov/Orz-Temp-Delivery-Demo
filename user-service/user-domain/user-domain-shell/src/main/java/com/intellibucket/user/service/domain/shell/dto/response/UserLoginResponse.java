package com.intellibucket.user.service.domain.shell.dto.response;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponse {
    private String email;
    private String username;
    private String userId;
}
