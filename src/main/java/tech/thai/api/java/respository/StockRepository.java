package tech.thai.api.java.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.thai.api.java.entity.Stock;

import java.util.UUID;

public interface StockRepository  extends JpaRepository<Stock, String> {


}
