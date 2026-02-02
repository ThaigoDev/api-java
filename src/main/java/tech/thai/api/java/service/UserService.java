package tech.thai.api.java.service;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tech.thai.api.java.dtos.CreateAccountDTO;
import tech.thai.api.java.dtos.CreateUserDto;
import tech.thai.api.java.dtos.UpdateUserDTO;
import tech.thai.api.java.entity.Account;
import tech.thai.api.java.entity.AccountStock;
import tech.thai.api.java.entity.BillingAdress;
import tech.thai.api.java.entity.User;
import tech.thai.api.java.respository.AccountRepository;
import tech.thai.api.java.respository.BillingAdressRepository;
import tech.thai.api.java.respository.UserRepository;

import java.time.Instant;
import java.util.*;

@Service
public class UserService {
    private UserRepository userRepository;
    private AccountRepository accountRepository;
    private BillingAdressRepository billingAdressRepository;

    public UserService(UserRepository userRepository, AccountRepository accountRepository, BillingAdressRepository billingAdressRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.billingAdressRepository = billingAdressRepository;
    }

    public UUID createUser(CreateUserDto createUserDTO) {
        var entity = new User(
                null,
                createUserDTO.username(),
                createUserDTO.email(),
                createUserDTO.password(),
                Instant.now(),
                null
        );
        var userSaved = userRepository.save(entity);
        return userSaved.getUserId();

    }

    public Optional<User> getUserById(UUID id) {
        var user = userRepository.findById(id);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(UUID userId) {
        var userExists = userRepository.existsById(userId);
        if (userExists) {
            userRepository.deleteById(userId);
        }
    }

    public void updateUserById(UUID userId, UpdateUserDTO updateUserDTO) {
        var userEntity = userRepository.findById(userId);
        if (userEntity.isPresent()) {
            var user = userEntity.get();
            if (updateUserDTO.username() != null) {
                user.setUsername(updateUserDTO.username());
            }
            if (updateUserDTO.password() != null) {
                user.setPassword(updateUserDTO.password());
            }
            userRepository.save(user);
        }


    }

    public void createAccount(UUID userId, CreateAccountDTO createAccountDTO) {
        var user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário não existe"));
        if(Objects.isNull(user.getAccounts())) {
            user.setAccounts(new ArrayList<>());
        }
        var account = new Account(
               UUID.randomUUID(),
                user,
                null,
                createAccountDTO.description(),
                new ArrayList<>()

        );
        var accountCreated = accountRepository.save(account);

        var billingAdress = new BillingAdress(
                accountCreated.getAccountId(),
                accountCreated,
                createAccountDTO.street(),
                createAccountDTO.number()

        );
        billingAdressRepository.save(billingAdress);
    }
    public List<Account> findAccounts(UUID userId) {
        var user = userRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não existe"));
        return user.getAccounts();
    };
}
