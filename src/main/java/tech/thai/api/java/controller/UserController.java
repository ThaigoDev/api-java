package tech.thai.api.java.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.thai.api.java.dtos.CreateUserDto;
import tech.thai.api.java.dtos.UpdateUserDTO;
import tech.thai.api.java.entity.User;
import tech.thai.api.java.service.UserService;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
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
    public ResponseEntity<Void> deleteUserById(@PathVariable("userId") String userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable("userId") String userId, @RequestBody UpdateUserDTO updateUserDTO) {
           userService.updateUserById(userId, updateUserDTO);
           return ResponseEntity.noContent().build();
    }
}
