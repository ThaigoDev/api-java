package tech.thai.api.java.dtos;

import java.util.UUID;

public record AccountStockDTO(UUID stockId, int quantity) {
}
