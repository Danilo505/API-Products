package com.example.models;

import com.example.dtos.products.RequestProduct;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "products")
@NoArgsConstructor
@Data
public class Products {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private Long id_product;

    private String name;

    private double price;

    @Column(name = "stock")
    private int quantity;

    public Products (RequestProduct product){
        this.name = product.name();
        this.price = product.price();
        this.quantity = product.quantity();
    }
}
