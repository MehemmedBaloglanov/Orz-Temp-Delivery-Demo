package com.intellibucket.adapter;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intellibucket.mapper.UserDataAccessMapper;
import com.intellibucket.model.CompanyRegistrationEntity;
import com.intellibucket.repository.CompanyJpaRepository;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserDataAccessMapper userDataAccessMapper;
    private final CompanyJpaRepository companyJpaRepository;

    @Override
    public Optional<UserRoot> findByUserId(UserID userId) {
        Optional<CompanyRegistrationEntity> user = companyJpaRepository.findById(userId.value());

        if (user.isEmpty()) {
            return Optional.empty();
        } else {
            CompanyRegistrationEntity userEntity = user.get();
            return Optional.of(userDataAccessMapper.companyEntityToUserRoot(userEntity));
        }
    }

    //    @Override
//    public UserRoot update(UserRoot userRoot) throws UserNotFoundException {
//
//        Optional<BaseUserEntity> user = companyJpaRepository.findById(userRoot.getUserID().value());
//
//        if (user.isEmpty()) {
//            throw new UserNotFoundException("User not found with email: " + userRoot.getUserID());
//        } else {
//            BaseUserEntity userEntity = user.get();
//            companyJpaRepository.updateBy(userEntity);
//            return userDataAccessMapper.userEntityToUserRoot(userEntity);
//        }
//    }
//
//    @Override
//    public UserRoot delete(UserRoot userRoot) throws UserNotFoundException {
//
//        Optional<BaseUserEntity> user = companyJpaRepository.findById(userRoot.getUserID().value());
//
//        if (user.isEmpty()) {
//            throw new UserNotFoundException("User not found with ID: " + userRoot.getUserID().value());
//        } else {
//            BaseUserEntity userEntity = user.get();
//            companyJpaRepository.delete(userEntity);
//            return userDataAccessMapper.userEntityToUserRoot(userEntity);
//        }
//    }
//
    @Override
    public UserRoot save(UserRoot userRoot) {
        CompanyRegistrationEntity userEntity = userDataAccessMapper.userRootToCompanyEntity(userRoot);
        CompanyRegistrationEntity savedUserEntity = companyJpaRepository.save(userEntity);
        return userDataAccessMapper.companyEntityToUserRoot(savedUserEntity);
    }

    @Override
    public Optional<UserRoot> findByEmail(Email email) {
        System.out.println("Email: " + email);
        Optional<CompanyRegistrationEntity> userEntityOptional = companyJpaRepository.findByEmail(email.getValue());

        if (userEntityOptional.isEmpty()) {
            return Optional.empty();
//            throw new UserNotFoundException("User not found with email: " + email.getValue());
        } else {
            CompanyRegistrationEntity companyRegistrationEntity = userEntityOptional.get();
            return Optional.of(userDataAccessMapper.companyEntityToUserRoot(companyRegistrationEntity));
        }
    }
}