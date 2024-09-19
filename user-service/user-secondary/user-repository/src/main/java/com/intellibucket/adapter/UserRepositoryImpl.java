package com.intellibucket.adapter;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.mapper.UserDataAccessMapper;
import com.intellibucket.model.UserEntity;
import com.intellibucket.repository.UserJpaRepository;
import com.intellibucket.service.UserRepository;
import com.intellibucket.user.service.domain.core.root.UserRoot;

import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<UserEntity> findByUserId(UserID userId) {
return null;

    }
    @Override
    public void registerAsCompany(UserRoot userRoot) {


}

    @Override
    public void registerAsCustomer(UserRoot userRoot) {

    }

    @Override
    public void update(UserRoot userRoot) {

    }

    @Override
    public void delete(UserRoot userRoot) {

    }

    @Override
    public void changePassword(UserRoot userRoot) {
//        // Assuming UserEntity has a password field and UserDataAccessMapper maps password correctly
//        UserEntity userEntity = UserJpaRepository.findById(userRoot.getUserID())
//                .orElseThrow(() -> new IllegalArgumentException("User not found"));
//        userEntity.setPassword(userRoot.getPassword().getValue());
//        UserJpaRepository.save(userEntity);

    }
}
