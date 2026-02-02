package tech.thai.api.java.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.thai.api.java.dtos.AccountResponseDTO;
import tech.thai.api.java.dtos.CreateAccountDTO;
import tech.thai.api.java.dtos.CreateUserDto;
import tech.thai.api.java.dtos.UpdateUserDTO;
import tech.thai.api.java.entity.Account;
import tech.thai.api.java.entity.User;
import tech.thai.api.java.service.UserService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) {
      var userId = userService.createUser(createUserDto);
        return ResponseEntity.created(URI.create("/v1/users/"+userId.toString())).build();
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") UUID userId) {
        var user =  userService.getUserById(userId);
        if(user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers () {
       var allUsers = userService.getAllUsers();
       return ResponseEntity.ok(allUsers);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("userId") UUID userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable("userId") UUID userId, @RequestBody UpdateUserDTO updateUserDTO) {
           userService.updateUserById(userId, updateUserDTO);
           return ResponseEntity.noContent().build();
    }
    @PostMapping("/{userId}/accounts")
    public ResponseEntity<Void> createAccount (@PathVariable("userId") UUID userId, @RequestBody CreateAccountDTO createAccountDTO) {
        System.out.println(userId);
       userService.createAccount(userId, createAccountDTO);
       return  ResponseEntity.ok().build();
    };
    @GetMapping("/{userId}/accounts")
    public ResponseEntity<List<AccountResponseDTO>> getAllAccounts(@PathVariable("userId") UUID userId) {
        var allAccounts = userService.findAccounts(userId).stream().map(ac-> new AccountResponseDTO(ac.getAccountId(), ac.getDescription())).toList();
        return ResponseEntity.ok(allAccounts);
    }
}
