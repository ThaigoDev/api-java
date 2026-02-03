package tech.thai.api.java.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tech.thai.api.java.dtos.AccountResponseDTO;
import tech.thai.api.java.dtos.AccountStockDTO;
import tech.thai.api.java.dtos.AccountStockResponseDTO;
import tech.thai.api.java.entity.AccountStock;
import tech.thai.api.java.entity.AccountStockId;
import tech.thai.api.java.respository.AccountRepository;
import tech.thai.api.java.respository.AccountStockRepository;
import tech.thai.api.java.respository.StockRepository;

import java.util.List;
import java.util.UUID;
@Service
public class AccountService {

    private StockRepository stockRepository;
    private AccountRepository accountRepository;
    private AccountStockRepository accountStockRepository;

    public AccountService(StockRepository stockRepository, AccountRepository accountRepository, AccountStockRepository accountStockRepository) {
        this.stockRepository = stockRepository;
        this.accountRepository = accountRepository;
        this.accountStockRepository = accountStockRepository;
    }


    public void associateStock(UUID accountId, AccountStockDTO accountStockDTO) {

     var account = accountRepository.findById(accountId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account não existe"));

     var stock = stockRepository.findById(accountStockDTO.stockId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock não encontrado"));

      var id  = new AccountStockId( account.getAccountId(), stock.getStock_id());
      var accountStockEntity = new AccountStock(id, account, stock, accountStockDTO.quantity());
      accountStockRepository.save(accountStockEntity);

    }

    public List<AccountStockResponseDTO> listStocks(UUID userId) {
     var account  =  accountRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada!"));
      return account.getAccountStocks().stream().map(ac -> new AccountStockResponseDTO(ac.getId().getStock_id(), ac.getQuantity(),0.0)).toList();
    }
}
