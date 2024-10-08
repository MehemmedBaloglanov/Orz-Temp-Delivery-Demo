package com.intellibucket.user.service.domain.shell.dto.query;

import com.intelliacademy.orizonroute.identity.user.UserID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FetchUserByIdCommand {
    UserID userId;
}