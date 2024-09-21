package com.intellibucket.user.service.domain.shell.dto.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDeleteCommand {
    private String userid;

}
