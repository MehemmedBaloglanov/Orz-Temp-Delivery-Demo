package com.intellibucket.user.service.primary.rest.command;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.exception.user.UserSavedException;
import com.intellibucket.user.service.domain.shell.dto.request.*;
import com.intellibucket.user.service.domain.shell.dto.response.EmptyResponse;
import com.intellibucket.user.service.domain.shell.dto.response.UserLoginResponse;
import com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.command.UserCommandServicePort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/1.0/users")
@RequiredArgsConstructor
public class UserCommandController {
    private final UserCommandServicePort abstractUserCommandService;

    @PostMapping("/register/company")
    public ResponseEntity<EmptyResponse> registerCompany(@RequestBody CompanyCreateCommand command) throws UserDomainException {
        abstractUserCommandService.companyRegistered(command);
        EmptyResponse response = EmptyResponse.builder().message("User registered successfully !").success(true).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/customer")
    public ResponseEntity<EmptyResponse> registerCustomer(@RequestBody CustomerCreateCommand command) throws UserDomainException {
        abstractUserCommandService.customerRegistered(command);
        EmptyResponse response = EmptyResponse.builder().message("User registered successfully !").success(true).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/updateCustomer")
    public ResponseEntity<EmptyResponse> updateUser(@RequestBody  CustomerUpdateCommand command) throws UserNotFoundException, UserSavedException {
        abstractUserCommandService.updateCustomer(command);
        EmptyResponse response = EmptyResponse.builder().message("User updated successfully !").success(true).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/updateCompany")
    public ResponseEntity<EmptyResponse> updateUser(@RequestBody  CompanyUpdateCommand command) throws UserNotFoundException, UserSavedException {
        abstractUserCommandService.updateCompany(command);
        EmptyResponse response = EmptyResponse.builder().message("User updated successfully !").success(true).build();
        return ResponseEntity.ok(response);
    }


//    @DeleteMapping("/{userId}")
//    public ResponseEntity<EmptyResponse> deleteUser(@PathVariable UserID userID) throws UserDomainException {
//        abstractUserCommandService.deleteUser(userID);
//        return ResponseEntity.ok(EmptyResponse.builder().message("User deleted successfully !").success(true).build());
//    }
//
//    @PostMapping("/{id}/change-password")
//    public ResponseEntity<EmptyResponse> changePassword(@RequestBody UserChangePasswordCommand command) throws UserDomainException {
////        UserID userID = UserID.of(id); //FIXME abstractSecurity holderdan al.
//        abstractUserCommandService.changePassword(command);
//        EmptyResponse response = EmptyResponse.builder().message("User deleted successfully !").success(true).build();
//        return ResponseEntity.ok(response);
//    }
//
    @PostMapping("/login/{userId}")
    public ResponseEntity<UserLoginResponse> loginUser(@RequestBody UserLoginCommand command,String userId) throws UserDomainException {
        UserID userID1 = UserID.of(userId);
        abstractUserCommandService.userLoggedIn(command,userID1);
        UserLoginResponse response = new UserLoginResponse();
        return ResponseEntity.ok(response);
   }
}