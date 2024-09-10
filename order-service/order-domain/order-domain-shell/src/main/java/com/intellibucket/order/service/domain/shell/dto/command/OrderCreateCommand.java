package com.intellibucket.order.service.domain.shell.dto.rest.command;

import com.intellibucket.order.service.domain.core.valueobject.OrderAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class OrderCreateCommand {
    @NotNull
    public final UUID cartId;
}