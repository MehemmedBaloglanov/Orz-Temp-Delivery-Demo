package com.intellibucket.company.service.company.repository.mapper;

import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intellibucket.company.service.company.repository.entity.CompanyJpaAddress;
import com.intellibucket.company.service.company.repository.entity.CompanyJpaEntity;
import com.intellibucket.company.service.company.repository.entity.ProductJpaEntity;
import com.intellibucket.company.service.domain.core.root.CompanyRoot;
import com.intellibucket.company.service.domain.core.root.ProductRoot;
import com.intellibucket.company.service.domain.core.valueobject.CompanyAddress;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyDataAccessMapper {

    public CompanyJpaEntity mapCompanyRootToCompanyJpaEntity(CompanyRoot root) {
        return CompanyJpaEntity.builder()
                .companyId(root.getRootID().value())
                .status(root.getStatus())
                .amount(root.getBalance().getAmount())
                .companyAddress(mapCompanyAddressToCompanyJpaAddress(root.getAddress()))
                .products(mapProductRootListToProductJpaEntityList(root.getProducts()))
                .build();
    }

    public CompanyRoot mapCompanyJpaEntityToCompanyRoot(CompanyJpaEntity entity) {
        return CompanyRoot.builder()
                .address(mapCompanyJpaEntityToCompanyAddress(entity))
                .status(entity.getStatus())
                .products(mapProductJpaEntityListToProductRootList(entity.getProducts()))
                .balance(Money.of(entity.getAmount()))
                .build();
    }
    private CompanyJpaAddress mapCompanyAddressToCompanyJpaAddress(CompanyAddress address) {
        return CompanyJpaAddress.builder()
                .city(address.city())
                .street(address.street())
                .address(address.street())
                .build();
    }

    private CompanyAddress mapCompanyJpaEntityToCompanyAddress(CompanyJpaEntity entity) {
        return CompanyAddress.builder()
                .city(entity.getCompanyAddress().getCity())
                .street(entity.getCompanyAddress().getStreet())
                .address(entity.getCompanyAddress().getAddress())
                .build();
    }
    public List<ProductJpaEntity> mapProductRootListToProductJpaEntityList(List<ProductRoot> root) {
       return root.stream()
                .map(productRoot -> ProductJpaEntity.builder()
                        .productId(productRoot.getProductID().value())
                        .price(productRoot.getPrice().getAmount())
                        .quantity(productRoot.getQuantity())
                        .status(productRoot.getStatus())
                        .companyId(productRoot.getCompanyID().value())
                        .build()).collect(Collectors.toList());
    }

    public List<ProductRoot> mapProductJpaEntityListToProductRootList(List<ProductJpaEntity> entities) {
        return entities.stream()
                .map(productJpaEntity -> ProductRoot.builder()
                        .status(productJpaEntity.getStatus())
                        .stockQuantity(productJpaEntity.getStock())
                        .quantity(productJpaEntity.getQuantity())
                        .price(Money.of(productJpaEntity.getPrice()))
                        .build()).collect(Collectors.toList());
    }

    public ProductJpaEntity mapProductRootToProductJpaEntity(ProductRoot root) {
        return ProductJpaEntity.builder()
                .productId(root.getProductID().value())
                .price(root.getPrice().getAmount())
                .quantity(root.getQuantity())
                .status(root.getStatus())
                .companyId(root.getCompanyID().value())
                .build();
    }

    public ProductRoot mapProductJpaEntityToProductRoot(ProductJpaEntity entity) {
        return ProductRoot.builder()
                .status(entity.getStatus())
                .quantity(entity.getQuantity())
                .stockQuantity(entity.getQuantity())
                .price(Money.of(entity.getPrice()))
                .build();
    }
}
