package com.intellibucket.user.service.domain.shell.dto.query;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FetchUsersByStatusAndRoleCommand {
    String roleAuthority;
    String status;
}