//package com.intellibucket.company.service.domain.shell.mapper;
//
//import com.intelliacademy.orizonroute.identity.company.CompanyID;
//import com.intelliacademy.orizonroute.valueobjects.common.Money;
//import com.intellibucket.company.service.company.repository.entity.CompanyEntity;
//import com.intellibucket.company.service.company.repository.entity.ProductEntity;
//import com.intellibucket.company.service.domain.core.root.ProductRoot;
//import com.intellibucket.company.service.domain.core.valueobject.ProductStatus;
//import com.intellibucket.company.service.domain.shell.dto.ProductDTO;
//
//import java.math.BigDecimal;
//
//
//public class ProductMapper {
//
//
//    public static ProductDTO mapProductEntityToProductDTO(ProductEntity productEntity) {
//        return ProductDTO.builder()
//                .id(productEntity.getId())
//                .name(productEntity.getName())
//                .description(productEntity.getDescription())
//                .price(productEntity.getPrice())
//                .quantity(productEntity.getQuantity())
//                .status(productEntity.getStatus().toString())
//                .companyId(productEntity.getCompany().getId())
//                .build();
//    }
//
//    // DTO to Entity
//    public static ProductEntity mapProductDTOToProductEntity(ProductDTO productDTO, CompanyEntity companyEntity) {
//        return ProductEntity.builder()
//                .id(productDTO.getId())
//                .name(productDTO.getName())
//                .description(productDTO.getDescription())
//                .price(productDTO.getPrice())
//                .quantity(productDTO.getQuantity())
//                .status(ProductStatus.valueOf(productDTO.getStatus()))
//                .company(companyEntity)
//                .build();
//    }
//
//    // Domain to Entity
//    public static ProductEntity mapProductRootToProductEntity(ProductRoot productRoot, CompanyEntity companyEntity) {
//        return ProductEntity.builder()
//                .id(productRoot.getRootID().toString())
//                .name(productRoot.getName())
//                .description(productRoot.getDescription())
//                .price(productRoot.getPrice().getAmount())
//                .quantity(productRoot.getQuantity())
//                .status(productRoot.getStatus())
//                .company(companyEntity)
//                .build();
//    }
//
//    // Entity to Domain
//    public static ProductRoot mapProductEntityToProductRoot(ProductEntity productEntity) {
//        return ProductRoot.builder()
//                .name(productEntity.getName())
//                .description(productEntity.getDescription())
//                .price(Money.of(productEntity.getPrice()))
//                .quantity(productEntity.getQuantity())
//                .status(productEntity.getStatus())
//                .companyID(CompanyID.of(productEntity.getCompany().getId()))
//                .build();
//    }
//}

