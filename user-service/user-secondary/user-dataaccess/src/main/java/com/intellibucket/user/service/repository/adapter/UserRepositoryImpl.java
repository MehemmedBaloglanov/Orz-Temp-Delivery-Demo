package com.intellibucket.user.service.repository.adapter;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.core.valueObject.Status;
import com.intellibucket.user.service.domain.shell.dto.response.UserAddressResponse;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import com.intellibucket.user.service.repository.mapper.UserDataAccessMapper;
import com.intellibucket.user.service.repository.model.CompanyRegistrationEntity;
import com.intellibucket.user.service.repository.model.CustomerRegistrationEntity;
import com.intellibucket.user.service.repository.repository.CompanyJpaRepository;
import com.intellibucket.user.service.repository.repository.CustomerJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserDataAccessMapper userDataAccessMapper;
    private final CustomerJpaRepository customerJpaRepository;
    private final CompanyJpaRepository companyJpaRepository;


    @Override
    public UserRoot save(UserRoot userRoot) {
        if (userRoot.getRoleAuthorithy().isRoleCustomer()) {
            CustomerRegistrationEntity userEntity = userDataAccessMapper.userRootToCustomerEntity(userRoot);
            CustomerRegistrationEntity savedUserEntity = customerJpaRepository.save(userEntity);
            return userDataAccessMapper.customerEntityToUserRoot(savedUserEntity);
        } else if (userRoot.getRoleAuthorithy().isRoleCompany()) {
            CompanyRegistrationEntity userEntity = userDataAccessMapper.userRootToCompanyEntity(userRoot);
            CompanyRegistrationEntity savedUserEntity = companyJpaRepository.save(userEntity);
            return userDataAccessMapper.companyEntityToUserRoot(savedUserEntity);
        }
        return userRoot;
    }

    @Override
    public Optional<UserRoot> findByEmail(Email email, UserRoot userRoot) {
        if (userRoot.getRoleAuthorithy().isRoleCustomer()) {
            Optional<CustomerRegistrationEntity> userEntityOptional = customerJpaRepository.findByEmail(email.getValue());
            if (userEntityOptional.isPresent()) {
                CustomerRegistrationEntity userEntity = userEntityOptional.get();
                return Optional.of(userDataAccessMapper.customerEntityToUserRoot(userEntity));
            }
        }
        Optional<CompanyRegistrationEntity> userEntityOptional = companyJpaRepository.findByEmail(email.getValue());
        if (userEntityOptional.isPresent()) {
            CompanyRegistrationEntity userEntity = userEntityOptional.get();
            return Optional.of(userDataAccessMapper.companyEntityToUserRoot(userEntity));
        }
        return Optional.empty();
    }

    @Override
    public List<UserRoot> findByStatusAndRoleAuthority(Status status, RoleAuthorithy role) {
        if (role.isRoleCustomer()) {
            List<CustomerRegistrationEntity> customers = customerJpaRepository.findByStatusAndRoleAuthority(status, role);
            return customers.stream()
                    .map(userDataAccessMapper::customerEntityToUserRoot)
                    .collect(Collectors.toList());
        } else if (role.isRoleCompany()) {
            List<CompanyRegistrationEntity> companies = companyJpaRepository.findByStatusAndRoleAuthority(status, role);
            return companies.stream()
                    .map(userDataAccessMapper::companyEntityToUserRoot)
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    @Override
    public Optional<UserRoot> findByUserId(UserID userId) {
        Optional<CustomerRegistrationEntity> customer = customerJpaRepository.findById(userId.getId());
        if (customer.isPresent()) {
            CustomerRegistrationEntity userEntity = customer.get();
            return Optional.of(userDataAccessMapper.customerEntityToUserRoot(userEntity));
        }
        Optional<CompanyRegistrationEntity> company = companyJpaRepository.findById(userId.getId());
        if (company.isPresent()) {
            CompanyRegistrationEntity userEntity = company.get();
            return Optional.of(userDataAccessMapper.companyEntityToUserRoot(userEntity));
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserAddressResponse> findAddressByUserId(UserID userId) {
        Optional<CustomerRegistrationEntity> customer = customerJpaRepository.findById(userId.getId());
        if (customer.isPresent()) {
            CustomerRegistrationEntity userEntity = customer.get();
            return Optional.of(userDataAccessMapper.customerAddressToUserAddressResponse(userEntity));
        }
        Optional<CompanyRegistrationEntity> company = companyJpaRepository.findById(userId.getId());
        if (company.isPresent()) {
            CompanyRegistrationEntity userEntity = company.get();
            return Optional.of(userDataAccessMapper.companyAddressToUserAddressResponse(userEntity));
        }
        return Optional.empty();
    }
}