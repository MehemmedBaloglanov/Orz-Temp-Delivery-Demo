//import com.intelliacademy.orizonroute.identity.user.UserID;
//import com.intellibucket.user.service.domain.core.event.UserChangePasswordDomainEvent;
//import com.intellibucket.user.service.domain.core.event.UserLoggedInDomainEvent;
//import com.intellibucket.user.service.domain.core.event.UserRegisteredEvent;
//import com.intellibucket.user.service.domain.core.exception.UserDomainException;
//import com.intellibucket.user.service.domain.core.exception.user.UserValidationException;
//import com.intellibucket.user.service.domain.core.root.UserRoot;
//import com.intellibucket.user.service.domain.core.valueObject.Password;
//import com.intellibucket.user.service.domain.shell.UserDomainServiceImpl;
//import com.intellibucket.user.service.domain.shell.dto.command.CompanyRegisterCommand;
//import com.intellibucket.user.service.domain.shell.dto.command.CustomerRegisterCommand;
//import com.intellibucket.user.service.domain.shell.dto.command.UserChangePasswordCommand;
//import com.intellibucket.user.service.domain.shell.dto.command.UserLoginCommand;
//import com.intellibucket.user.service.domain.shell.mapper.UserCommandMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api/1.0/users")
//@RequiredArgsConstructor
//public class UserServiceController {
//    private final UserDomainServiceImpl userDomainServiceImpl;
//
//    @PostMapping("/register/company")
//    public ResponseEntity<UserRegisteredEvent> registerCompany(@RequestBody CompanyRegisterCommand command) {
//        try {
//            UserRoot user = UserCommandMapper.toUserRoot(command); // Map to UserRoot
//            UserRegisteredEvent event = userDomainServiceImpl.companyRegistered(user);
//            return new ResponseEntity<>(event, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
//
//    @PostMapping("/register/customer")
//    public ResponseEntity<UserRegisteredEvent> registerCustomer(@RequestBody CustomerRegisterCommand command) {
//        try {
//            UserRoot user = UserCommandMapper.toUserRoot(command); // Map to UserRoot
//            UserRegisteredEvent event = userDomainServiceImpl.customerRegistered(user);
//            return new ResponseEntity<>(event, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
//        try {
//            UserID userID = new UserID(id);
//            userDomainServiceImpl.userDeleted(userID);
//            return ResponseEntity.ok("User deleted successfully");
//        } catch (UserValidationException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting user");
//        }
//    }
//
//    @PostMapping("/{id}/change-password")
//    public ResponseEntity<UserChangePasswordDomainEvent> changePassword(@PathVariable UUID id, @RequestBody UserChangePasswordCommand command) {
//        try {
//            UserID userID = new UserID(id);
//            UserRoot user = userDomainServiceImpl.findByUserId(userID).orElseThrow(() -> new UserValidationException("User not found"));
//            Password newPassword = new Password(command.getNewPassword());
//            user.changePassword(newPassword);
//            UserChangePasswordDomainEvent event = userDomainServiceImpl.userChangePassword(user);
//            return ResponseEntity.ok(event);
//        } catch (UserDomainException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<UserLoggedInDomainEvent> loginUser(@RequestBody UserLoginCommand command) {
//        try {
//            UserRoot user = UserCommandMapper.toUserRoot(command); // Map to UserRoot
//            UserLoggedInDomainEvent event = userDomainServiceImpl.userLoggedIn(user);
//            return ResponseEntity.ok(event);
//        } catch (UserDomainException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
//}
