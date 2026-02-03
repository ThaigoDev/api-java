package tech.thai.api.java.service;

import org.springframework.stereotype.Service;
import tech.thai.api.java.dtos.CreateStockDTO;
import tech.thai.api.java.entity.Stock;
import tech.thai.api.java.respository.StockRepository;

@Service
public class StockService {
    private StockRepository stockRepository;
    public StockService ( StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }
    public void createStock(CreateStockDTO createStockDTO) {
        //conversão de DTO para Entity
        var stock = new Stock(

                 createStockDTO.stockId(),
                createStockDTO.description()

        );
        stockRepository.save(stock);
    }
}
