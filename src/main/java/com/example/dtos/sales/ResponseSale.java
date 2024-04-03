package com.example.dtos.sales;

import java.time.LocalDateTime;


public record ResponseSale(int id_product, int amount, double valueFinal, LocalDateTime date) {
}
