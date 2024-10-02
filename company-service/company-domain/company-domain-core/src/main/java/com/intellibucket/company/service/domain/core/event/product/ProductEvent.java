package com.intellibucket.company.service.domain.core.event.product;

import com.intellibucket.company.service.domain.core.root.ProductRoot;
import com.intellibucket.domain.event.DomainEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;


@RequiredArgsConstructor
@Getter
public class ProductEvent implements DomainEvent<ProductRoot> {
    private final ProductRoot productRoot;
    private final OffsetDateTime createTime;
}
