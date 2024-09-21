package com.intellibucket.adapter;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.mapper.UserDataAccessMapper;
import com.intellibucket.model.UserEntity;
import com.intellibucket.repository.UserJpaRepository;
import com.intellibucket.user.service.domain.core.root.UserRoot;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

private final UserDataAccessMapper userDataAccessMapper;
private final UserJpaRepository userJpaRepository;
    @Override
    public Optional<UserRoot> findByUserId(UserID userId) {
        Optional<UserEntity> userEntity = userJpaRepository.findById(userId.value());

        if (userEntity.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(userDataAccessMapper.userEntityToUserRoot(userEntity.get()));
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
    public Optional<UserRoot> save(UserRoot userRoot) {

        Optional<UserEntity> user = userJpaRepository.findById(userRoot.getUserID().value());

        if (!user.isEmpty()) {
            return Optional.empty();
        }else {
            UserEntity userEntity = user.get();
            return Optional.of(userDataAccessMapper.userEntityToUserRoot(userEntity));
        }
    }
}
