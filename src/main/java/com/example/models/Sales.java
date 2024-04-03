package com.example.models;


import com.example.dtos.sales.RequestSale;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;


@Entity(name = "sales")
@Table(name = "sales")
@NoArgsConstructor
@Data
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_sale;
    private int id_product;
    private int amount;
    private double valueFinal;
    private LocalDateTime date;

    public Sales(RequestSale sale){
        this.id_product = sale.id_product();
        this.amount = sale.amount();
        this.date = LocalDateTime.now();
    }

}
