package tech.thai.api.java.service;

import org.springframework.stereotype.Service;
import tech.thai.api.java.dtos.CreateUserDto;
import tech.thai.api.java.dtos.UpdateUserDTO;
import tech.thai.api.java.entity.User;
import tech.thai.api.java.respository.UserRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;
    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UUID createUser(CreateUserDto createUserDTO) {
     var entity =  new User(
             null,
             createUserDTO.username(),
             createUserDTO.email(),
             createUserDTO.password(),
             Instant.now(),
             null
     );
     var userSaved =    userRepository.save(entity);
     return userSaved.getUserId();

    }
    public Optional<User> getUserById(String id) {
          var user = userRepository.findById(UUID.fromString(id));
          return  user;
    }
    public List<User> getAllUsers () {
        return  userRepository.findAll();
    }
    public void deleteUserById(String userId) {
        var id = UUID.fromString(userId);
        var userExists = userRepository.existsById(id);
        if(userExists) {
            userRepository.deleteById(id);
        }
    }
    public void updateUserById(String userId, UpdateUserDTO updateUserDTO ) {
        var id = UUID.fromString(userId);
         var userEntity= userRepository.findById(id);
         if(userEntity.isPresent()) {
             var user = userEntity.get();
             if(updateUserDTO.username()!= null) {
                 user.setUsername(updateUserDTO.username());
             }
             if(updateUserDTO.password()!= null) {
                 user.setPassword(updateUserDTO.password());
             }
             userRepository.save(user);
         }


    }
}
