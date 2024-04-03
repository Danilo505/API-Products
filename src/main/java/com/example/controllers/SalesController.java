package com.example.controllers;

import com.example.dtos.sales.RequestSale;
import com.example.dtos.sales.ResponseSale;
import com.example.salesService.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesController {
    private final SaleService saleService;

    public SalesController(SaleService saleService) {this.saleService = saleService;}

    @PostMapping
    public ResponseEntity<ResponseSale> makeSale(@RequestBody RequestSale requestSale)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(saleService.makeSale(requestSale));
    }

    @GetMapping
    public ResponseEntity<List<ResponseSale>> getAllSales()

    {
        return ResponseEntity.ok(saleService.getAllSales());
    }
}
