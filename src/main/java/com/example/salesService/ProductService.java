package com.example.salesService;

import com.example.dtos.products.RequestProduct;
import com.example.dtos.products.ResponseProduct;
import com.example.models.Products;
import com.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ResponseProduct> getAllProducts() {
        return convert(repository.findAll());
    }

    public List<ResponseProduct> convert(List<Products> products) {
        return products
                .stream()
                .map(product -> new ResponseProduct(product.getId_product(), product.getName(), product.getPrice(), product.getQuantity()))
                .collect(Collectors.toList());
    }

    public ResponseProduct getProductByID(int id) {
        var product = repository.findById(id).orElseThrow();
        return new ResponseProduct(product.getId_product(), product.getName(), product.getPrice(), product.getQuantity());
    }

    public ResponseProduct productPost(RequestProduct requestProduct) {
        Products products = new Products(requestProduct);

        if (requestProduct.quantity() < 0 || requestProduct.price() < 0)
            return null;

        repository.save(products);

        return new ResponseProduct(products.getId_product(), products.getName(), products.getPrice(), products.getQuantity());
    }

    public ResponseProduct updateProduct(int id, RequestProduct requestProduct) {
        Products existingProduct = repository.findById(id).orElse(null);
        if (existingProduct == null) {
            return null;
        }

        existingProduct.setName(requestProduct.name());
        existingProduct.setPrice(requestProduct.price());
        existingProduct.setQuantity(requestProduct.quantity());
        Products updatedProduct = repository.save(existingProduct);
        return new ResponseProduct(updatedProduct.getId_product(), updatedProduct.getName(), updatedProduct.getPrice(), updatedProduct.getQuantity());
    }

    public boolean deleteProduct(int id){
        Products existingProduct = repository.findById(id).orElseThrow(null);
        if (existingProduct != null) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}




