package com.intellibucket.company.service.domain.shell.dto.rest.query;

import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductListWithIdQuery {
    List<ProductID> productIDList;

}
