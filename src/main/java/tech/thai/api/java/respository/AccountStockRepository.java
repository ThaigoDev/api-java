package tech.thai.api.java.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.thai.api.java.entity.AccountStock;
import tech.thai.api.java.entity.AccountStockId;

import java.util.UUID;

public interface AccountStockRepository  extends JpaRepository<AccountStock, AccountStockId> {

}
