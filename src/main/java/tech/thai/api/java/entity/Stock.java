package tech.thai.api.java.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_stocks")
public class Stock {
    @Id
    @Column(name = "stock_id")
    private UUID stock_id;

    @Column(name = "description")
    private String description;

    @Column(name= "ticker")
    private String ticker;

    public Stock() {
    }

    public Stock(UUID stock_id, String description, String ticker) {
        this.stock_id = stock_id;
        this.description = description;
        this.ticker = ticker;
    }

    public UUID getStock_id() {
        return stock_id;
    }

    public void setStock_id(UUID stock_id) {
        this.stock_id = stock_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
}
