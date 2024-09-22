package com.intellibucket.adapter;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intelliacademy.orizonroute.valueobjects.common.Email;
import com.intellibucket.mapper.UserDataAccessMapper;
import com.intellibucket.model.UserEntity;
import com.intellibucket.repository.UserJpaRepository;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

private final UserDataAccessMapper userDataAccessMapper;
private final UserJpaRepository userJpaRepository;
    @Override

    public Optional<UserRoot> findByUserId(UserID userId) {
        Optional<UserEntity> user = userJpaRepository.findById(userId.value());

        if (user.isEmpty()) {
            return Optional.empty();
        } else {
            UserEntity userEntity = user.get();
            return Optional.of(userDataAccessMapper.userEntityToUserRoot(userEntity));
        }
    }


    @Override
    public Optional<UserRoot> update(UserRoot userRoot) {

        Optional<UserEntity> user = userJpaRepository.findById(userRoot.getUserID().value());

        if (user.isEmpty()) {
            return Optional.empty();
        } else {
            UserEntity userEntity = user.get();
            userJpaRepository.updateBy(userEntity);
            return Optional.of(userDataAccessMapper.userEntityToUserRoot(userEntity));
        }
    }

    @Override
    public Optional<UserRoot> delete(UserRoot userRoot) {

        Optional<UserEntity> user = userJpaRepository.findById(userRoot.getUserID().value());

        if (user.isEmpty()) {
            return Optional.empty();
        } else {
            UserEntity userEntity = user.get();
            userJpaRepository.delete(userEntity);
            return Optional.of(userDataAccessMapper.userEntityToUserRoot(userEntity));
        }
    }

    @Override
    public Optional <UserRoot> save(UserRoot userRoot) {

        UserEntity userEntity = userDataAccessMapper.userRootToUserEntity(userRoot);
        UserEntity savedUserEntity = userJpaRepository.save(userEntity);
        return Optional.ofNullable(userDataAccessMapper.userEntityToUserRoot(savedUserEntity));
    }

    @Override
    public Optional<UserRoot> findByEmail(Email email) {
        Optional<UserEntity> userEntityOptional = userJpaRepository.findByEmail(email.getValue());

        if (userEntityOptional.isEmpty()) {
            return Optional.empty();
        } else {
            UserEntity userEntity = userEntityOptional.get();
            return Optional.of(userDataAccessMapper.userEntityToUserRoot(userEntity));
        }
    }


}
