package com.example.controllers;

import com.example.dtos.products.RequestProduct;
import com.example.dtos.products.ResponseProduct;
import com.example.salesService.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("products")

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity<List<ResponseProduct>> getAll(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseProduct> getProductById(@PathVariable int id){
        return ResponseEntity.ok(productService.getProductByID(id));
    }

    @PostMapping
    public ResponseEntity<ResponseProduct> postProduct(@RequestBody @Valid RequestProduct requestProduct){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.productPost(requestProduct));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseProduct> updateProduct(@PathVariable int id, @RequestBody @Valid RequestProduct requestProduct) {
        ResponseProduct updatedProduct = productService.updateProduct(id, requestProduct);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseProduct> deleteProduct(@PathVariable int id){
        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

