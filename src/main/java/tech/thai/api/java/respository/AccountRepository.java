package tech.thai.api.java.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.thai.api.java.entity.Account;

import java.util.UUID;

public interface AccountRepository  extends JpaRepository<Account, UUID> {


}
