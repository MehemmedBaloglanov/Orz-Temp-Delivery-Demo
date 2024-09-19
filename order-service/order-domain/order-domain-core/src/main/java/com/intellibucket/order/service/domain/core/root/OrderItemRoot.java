package com.intellibucket.order.service.domain.core.root;

import com.intelliacademy.orizonroute.identity.company.CompanyID;
import com.intelliacademy.orizonroute.identity.order.ord.OrderID;
import com.intelliacademy.orizonroute.identity.order.ord.OrderItemID;
import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.root.AggregateRoot;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Builder
@Getter
public class OrderItemRoot extends AggregateRoot<OrderItemID> {
    private OrderID orderId;
    private CompanyID companyID;
    private final ProductID productID;
    private final Integer quantity;
    private final Money price;
    private final Money subTotal;


    public boolean isPriceValid() {
        return price.isGreaterThanZero()
                && price.multiply(BigDecimal.valueOf(quantity)).isEqualTo(subTotal);
    }
}
