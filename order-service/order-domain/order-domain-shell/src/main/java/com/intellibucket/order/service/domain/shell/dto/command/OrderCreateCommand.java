package com.intellibucket.order.service.domain.shell.dto.command;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class OrderCreateCommand {
    @NotNull
    public final UUID cartId;
}