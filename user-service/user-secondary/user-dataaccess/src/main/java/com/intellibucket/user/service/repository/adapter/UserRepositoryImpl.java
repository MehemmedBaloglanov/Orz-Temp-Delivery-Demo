package com.intellibucket.user.service.repository.adapter;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.core.valueObject.RoleAuthorithy;
import com.intellibucket.user.service.domain.core.valueObject.Status;
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

//@Component
@Slf4j
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserDataAccessMapper userDataAccessMapper;
    private final CustomerJpaRepository customerJpaRepository;
    private final CompanyJpaRepository companyJpaRepository;

    @Override
    public Optional<UserRoot> findByCompanyId(UserID userId) {
        if (RoleAuthorithy.COMPANY.isRoleCompany()) {
            Optional<CompanyRegistrationEntity> user = companyJpaRepository.findById(userId.value());

            if (user.isEmpty()) {
                log.warn("Company not found for ID: {}", userId.value());
                return Optional.empty();
            } else {
                CompanyRegistrationEntity userEntity = user.get();
                return Optional.of(userDataAccessMapper.companyEntityToUserRoot(userEntity));
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserRoot> findByCustomerId(UserID userId) {
        log.info("Fetching user with ID: {}", userId.value());

        if (RoleAuthorithy.CUSTOMER.isRoleCustomer()) {
            Optional<CustomerRegistrationEntity> user = customerJpaRepository.findById(userId.value());

            if (user.isEmpty()) {
                log.warn("Customer not found for ID: {}", userId.value());
                return Optional.empty();
            }
            else {
                CustomerRegistrationEntity userEntity = user.get();
                return Optional.of(userDataAccessMapper.customerEntityToUserRoot(userEntity));
            }
        }

        log.warn("User ID {} not found for any role", userId.value());
        return Optional.empty();
    }



    @Override
    public UserRoot update(UserRoot userRoot) throws UserNotFoundException {
        if (RoleAuthorithy.CUSTOMER.isRoleCustomer()) {
            Optional<CustomerRegistrationEntity> user = customerJpaRepository.findById(userRoot.getUserID().value());

            if (user.isEmpty()) {
                throw new UserNotFoundException("User not found with ID: " + userRoot.getUserID());
            } else {
                CustomerRegistrationEntity userEntity = user.get();
                customerJpaRepository.save(userEntity);
                return userDataAccessMapper.customerEntityToUserRoot(userEntity);
            }
        } else if (RoleAuthorithy.COMPANY.isRoleCompany()) {

            Optional<CompanyRegistrationEntity> user = companyJpaRepository.findById(userRoot.getUserID().value());

            if (user.isEmpty()) {
                throw new UserNotFoundException("User not found with ID: " + userRoot.getUserID());
            } else {
                CompanyRegistrationEntity userEntity = user.get();
                companyJpaRepository.save(userEntity);
                return userDataAccessMapper.companyEntityToUserRoot(userEntity);
            }

        }

        return userRoot;
    }

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
    public Optional<UserRoot> findByEmail(Email email, UserRoot userRoot) throws UserNotFoundException {
        if (userRoot.getRoleAuthorithy().isRoleCustomer()) {
            System.out.println("inside findByEmail and check if-else!");
            Optional<CustomerRegistrationEntity> userEntityOptional = customerJpaRepository.findByEmail(email.getValue());
            if (userEntityOptional.isEmpty()) {
                return Optional.empty();
            } else {
                CustomerRegistrationEntity userEntity = userEntityOptional.get();
                return Optional.of(userDataAccessMapper.customerEntityToUserRoot(userEntity));
            }
        } else if (userRoot.getRoleAuthorithy().isRoleCompany()) {
            Optional<CompanyRegistrationEntity> userEntityOptional = companyJpaRepository.findByEmail(email.getValue());
            if (userEntityOptional.isEmpty()) {
                return Optional.empty();
            } else {
                CompanyRegistrationEntity userEntity = userEntityOptional.get();
                return Optional.of(userDataAccessMapper.companyEntityToUserRoot(userEntity));
            }
        }
        return Optional.empty();
    }

    @Override
    public List<RoleAuthorithy> findByAuthority(String name) {
        return List.of();
    }

    @Override
    public List<Status> findByStatusAndRoleAuthority(Status status, RoleAuthorithy role) {
        return List.of();
    }
}