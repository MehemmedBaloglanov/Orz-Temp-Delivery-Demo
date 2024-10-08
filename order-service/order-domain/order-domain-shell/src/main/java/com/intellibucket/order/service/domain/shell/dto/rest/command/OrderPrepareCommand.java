package com.intellibucket.order.service.domain.shell.dto.rest.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@ToString
@Builder
@Data
@AllArgsConstructor
<<<<<<<< HEAD:order-service/order-domain/order-domain-shell/src/main/java/com/intellibucket/order/service/domain/shell/dto/rest/command/OrderConfirmCommand.java
public class OrderConfirmCommand {
========
@NoArgsConstructor
public class OrderPrepareCommand {
>>>>>>>> origin/develop:order-service/order-domain/order-domain-shell/src/main/java/com/intellibucket/order/service/domain/shell/dto/rest/command/OrderPrepareCommand.java

    @JsonProperty("order_id")
    private String orderId;

<<<<<<<< HEAD:order-service/order-domain/order-domain-shell/src/main/java/com/intellibucket/order/service/domain/shell/dto/rest/command/OrderConfirmCommand.java
    @JsonProperty("order_id")
    private String orderItemId;
========
    @JsonProperty("order_item_id")
    private String orderItemId;

>>>>>>>> origin/develop:order-service/order-domain/order-domain-shell/src/main/java/com/intellibucket/order/service/domain/shell/dto/rest/command/OrderPrepareCommand.java
}
