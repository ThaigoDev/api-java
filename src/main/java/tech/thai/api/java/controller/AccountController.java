package tech.thai.api.java.controller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.thai.api.java.dtos.AccountStockDTO;
import tech.thai.api.java.dtos.AccountStockResponseDTO;
import tech.thai.api.java.entity.User;
import tech.thai.api.java.service.AccountService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController  {
    private AccountService accountService;

    public AccountController (AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{accountId}/stocks")
    public ResponseEntity<User> associateStock(@PathVariable("accountId")UUID accountId, @RequestBody AccountStockDTO accountStockDTO) {
        accountService.associateStock(accountId, accountStockDTO);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{userId}/stocks")
    public ResponseEntity<List<AccountStockResponseDTO>> getAllStocksByUser(@PathVariable("userId") UUID userId) {
       var stocks = accountService.listStocks(userId);
       return ResponseEntity.ok().build();
    }

}
