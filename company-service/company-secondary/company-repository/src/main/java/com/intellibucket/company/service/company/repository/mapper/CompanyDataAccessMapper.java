package com.intellibucket.company.service.company.repository.mapper;

import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intelliacademy.orizonroute.valueobjects.common.Money;
import com.intellibucket.company.service.company.repository.entity.ProductJpaEntity;
import com.intellibucket.company.service.domain.core.root.ProductRoot;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyDataAccessMapper {

//    public CompanyJpaEntity mapCompanyRootToCompanyJpaEntity(CompanyRoot root) {
//        return CompanyJpaEntity.builder()
//                .companyId(root.getRootID().value())
//                .companyName(root.getCompanyName().value())
//                .status(root.getStatus())
//                .balance(root.getBalance().getAmount())
//                .address(mapCompanyAddressToCompanyJpaAddress(root.getAddress()))
//                .products(mapProductRootListToProductJpaEntityList(root.getProducts()))
//                .build();
//    }
//
//    public CompanyRoot mapCompanyJpaEntityToCompanyRoot(CompanyJpaEntity entity) {
//        return CompanyRoot.builder()
//                .id(CompanyID.of(entity.getCompanyId()))
//                .companyName(Username.of(entity.getCompanyName()))
//                .address(mapCompanyJpaEntityToCompanyAddress(entity))
//                .status(entity.getStatus())
//                .products(mapProductJpaEntityListToProductRootList(entity.getProducts()))
//                .balance(Money.of(entity.getBalance()))
//                .build();
//    }
//
//    private CompanyJpaAddress mapCompanyAddressToCompanyJpaAddress(CompanyAddress address) {
//        return CompanyJpaAddress.builder()
//                .city(address.getCity())
//                .street(address.getStreet())
//                .address(address.getAddress())
//                .build();
//    }
//
//    private CompanyAddress mapCompanyJpaEntityToCompanyAddress(CompanyJpaEntity entity) {
//        return CompanyAddress.builder()
//                .city(entity.getAddress().getCity())
//                .street(entity.getAddress().getStreet())
//                .address(entity.getAddress().getAddress())
//                .build();
//    }


    public List<ProductJpaEntity> mapProductRootListToProductJpaEntityList(List<ProductRoot> root) {
       return root.stream()
                .map(productRoot -> ProductJpaEntity.builder()
                        .productId(productRoot.getRootID().value())
                        .name(productRoot.getName())
                        .price(productRoot.getPrice().getAmount())
                        .status(productRoot.getStatus())
//                        .companyId(productRoot.getCompanyID().value())
                        .build()).collect(Collectors.toList());
    }

    public List<ProductRoot> mapProductJpaEntityListToProductRootList(List<ProductJpaEntity> entities) {
        return entities.stream()
                .map(productJpaEntity -> ProductRoot.builder()
                        .name(productJpaEntity.getName())
                        .id(ProductID.of(productJpaEntity.getProductId()))
//                        .companyID(CompanyID.of(productJpaEntity.getCompanyId()))
                        .status(productJpaEntity.getStatus())
                        .stockQuantity(productJpaEntity.getStockQuantity())
                        .price(Money.of(productJpaEntity.getPrice()))
                        .build()).collect(Collectors.toList());
    }

    public ProductJpaEntity mapProductRootToProductJpaEntity(ProductRoot root) {
        return ProductJpaEntity.builder()
                .name(root.getName())
                .productId(root.getRootID().value())
                .price(root.getPrice().getAmount())
                .stockQuantity(root.getStockQuantity())
                .status(root.getStatus())
//                .companyId(root.getCompanyID().value())
                .build();
    }

    public ProductRoot mapProductJpaEntityToProductRoot(ProductJpaEntity entity) {
        return ProductRoot.builder()
                .name(entity.getName())
//                .companyID(CompanyID.of(entity.getCompanyId()))
                .status(entity.getStatus())
                .stockQuantity(entity.getStockQuantity())
                .price(Money.of(entity.getPrice()))
                .build();
    }
}
