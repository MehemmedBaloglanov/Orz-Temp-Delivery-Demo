package com.intellibucket.company.service.company.repository.adapter;

import com.intelliacademy.orizonroute.identity.order.product.ProductID;
import com.intellibucket.company.service.company.repository.entity.ProductJpaEntity;
import com.intellibucket.company.service.company.repository.mapper.CompanyDataAccessMapper;
import com.intellibucket.company.service.company.repository.repository.ProductJpaRepository;
import com.intellibucket.company.service.domain.core.exception.CompanyDomainException;
import com.intellibucket.company.service.domain.core.root.ProductRoot;
import com.intellibucket.company.service.domain.shell.port.output.repository.ProductRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapterImpl implements ProductRepositoryAdapter {

    private final ProductJpaRepository productJpaRepository;
    private final CompanyDataAccessMapper companyDataAccessMapper;

    @Override
    public ProductRoot save(ProductRoot productRoot) {
        ProductJpaEntity productJpaEntity =companyDataAccessMapper.mapProductRootToProductJpaEntity(productRoot);
        ProductJpaEntity save = productJpaRepository.save(productJpaEntity);
        return companyDataAccessMapper.mapProductJpaEntityToProductRoot(save);
    }

    @Override
    public Optional<ProductRoot> findById(ProductID productId) {
        Optional<ProductJpaEntity> product = productJpaRepository.findById(productId.value());
        if(product.isEmpty()){
            return Optional.empty();
        }else{
            ProductJpaEntity productJpaEntity = product.get();
            return Optional.of(companyDataAccessMapper.mapProductJpaEntityToProductRoot(productJpaEntity));
        }
    }

    @Override
    public List<ProductRoot> findAll() {
        return productJpaRepository.findAll().stream().map(
                companyDataAccessMapper::mapProductJpaEntityToProductRoot
        ).toList();
    }

    @Override
    public void deleteById(ProductID productID) throws CompanyDomainException {
        Optional<ProductJpaEntity> product= productJpaRepository.findById(productID.value());
        if(product.isEmpty()){
            throw new CompanyDomainException("Company does not found with id: " + productID.value());
        }else {
            ProductRoot productRoot = companyDataAccessMapper.mapProductJpaEntityToProductRoot(product.get());
            ProductRoot delete = productRoot.delete();
            ProductJpaEntity productJpaEntity =  companyDataAccessMapper.mapProductRootToProductJpaEntity(delete);
            productJpaRepository.save(productJpaEntity);
        }
    }
}
