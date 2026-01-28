package tech.thai.api.java.controller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.thai.api.java.entity.User;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody String body) {
        return null;
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
        return null;
    }
}
