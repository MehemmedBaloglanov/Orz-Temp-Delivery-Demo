package com.intellibucket.user.service.domain.shell.handler.query;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.shell.dto.query.FetchUserAddressByIdCommand;
import com.intellibucket.user.service.domain.shell.dto.response.UserAddressResponse;
import com.intellibucket.user.service.domain.shell.port.output.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FetchUserAddressById {
    private final UserRepository userRepository;

    public Optional<UserAddressResponse> handle(FetchUserAddressByIdCommand command) throws UserNotFoundException {

        UserID userId = command.getUserId();

        Optional<UserAddressResponse> userAddress = userRepository.findAddressByUserId(userId);
        if (userAddress.isEmpty()) {
            throw new UserNotFoundException("ID is: " + userId);
        }
        return userAddress;
    }
}