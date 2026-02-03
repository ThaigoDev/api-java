package tech.thai.api.java.dtos;

import java.util.UUID;

public record AccountStockResponseDTO(String stockId, int quantity, double total) {
}
