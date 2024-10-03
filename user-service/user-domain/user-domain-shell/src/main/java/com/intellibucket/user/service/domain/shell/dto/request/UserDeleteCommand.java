package com.intellibucket.user.service.domain.shell.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDeleteCommand {
    private String userID ;
    private String email;
}