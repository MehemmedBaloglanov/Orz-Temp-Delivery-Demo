package com.intellibucket.user.service.domain.shell.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmptyResponse {
    private String message;
    private boolean success;
}