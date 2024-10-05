package com.intellibucket.user.service.primary.rest.command;

import com.intelliacademy.orizonroute.identity.user.UserID;
import com.intellibucket.user.service.domain.core.exception.UserDomainException;
import com.intellibucket.user.service.domain.core.exception.user.UserNotFoundException;
import com.intellibucket.user.service.domain.core.exception.user.UserSavedException;
import com.intellibucket.user.service.domain.shell.dto.request.*;
import com.intellibucket.user.service.domain.shell.dto.response.EmptyResponse;
import com.intellibucket.user.service.domain.shell.dto.response.UserLoginResponse;
import com.intellibucket.user.service.domain.shell.port.input.rest.abstracts.command.UserCommandServicePort;
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

    @PostMapping("/update/Customer")
    public ResponseEntity<EmptyResponse> updateUser(@RequestBody  CustomerUpdateCommand command) throws UserNotFoundException, UserSavedException {
        abstractUserCommandService.updateCustomer(command);
        EmptyResponse response = EmptyResponse.builder().message("User updated successfully !").success(true).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update/Company")
    public ResponseEntity<EmptyResponse> updateUser(@RequestBody  CompanyUpdateCommand command) throws UserNotFoundException, UserSavedException {
        abstractUserCommandService.updateCompany(command);
        EmptyResponse response = EmptyResponse.builder().message("User updated successfully !").success(true).build();
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/delete/customer")
    public ResponseEntity<EmptyResponse> deleteCustomer(@RequestBody UserDeleteCommand command) throws UserDomainException {
        abstractUserCommandService.customerDelete(command);
        return ResponseEntity.ok(EmptyResponse.builder().message("User deleted successfully !").success(true).build());
    }

    @DeleteMapping("/delete/company")
    public ResponseEntity<EmptyResponse> deleteCompany(@RequestBody UserDeleteCommand command) throws UserDomainException {
        abstractUserCommandService.companyDelete(command);
        return ResponseEntity.ok(EmptyResponse.builder().message("User deleted successfully !").success(true).build());
    }

    @PostMapping("/customer/change/password")
    public ResponseEntity<EmptyResponse> customerChangePassword(@RequestBody UserChangePasswordCommand command) throws UserDomainException {
        abstractUserCommandService.customerChangePassword(command);
        EmptyResponse response = EmptyResponse.builder().message("User deleted successfully !").success(true).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/company/change/password")
    public ResponseEntity<EmptyResponse> companyChangePassword(@RequestBody UserChangePasswordCommand command) throws UserDomainException {
        abstractUserCommandService.companyChangePassword(command);
        EmptyResponse response = EmptyResponse.builder().message("User deleted successfully !").success(true).build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login/{userId}")
    public ResponseEntity<UserLoginResponse> loginUser(@RequestBody UserLoginCommand command,String userId) throws UserDomainException {
        UserID userID1 = UserID.of(userId);
        abstractUserCommandService.userLoggedIn(command,userID1);
        UserLoginResponse response = new UserLoginResponse();
        return ResponseEntity.ok(response);
   }
}