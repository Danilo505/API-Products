package com.example.salesService;

import com.example.dtos.sales.RequestSale;
import com.example.dtos.sales.ResponseSale;
import com.example.models.Sales;
import com.example.repository.ProductRepository;
import com.example.repository.SalesRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleService {
    private final SalesRepository salesRepository;
    private final ProductRepository productRepository;

    public SaleService(ProductRepository productRepository, SalesRepository salesRepository) {
        this.productRepository = productRepository;
        this.salesRepository = salesRepository;
    }

    public ResponseSale makeSale(RequestSale requestSale){
        if(SaleValid(requestSale)){
            var newSale = new Sales(requestSale);

            newSale.setValueFinal(discountPrice(requestSale));
            updateProduct(requestSale);

            return  new ResponseSale(newSale.getId_product(), newSale.getAmount(),newSale.getValueFinal(), newSale.getDate());

        }
        return null;
    }

    public double discountPrice(RequestSale requestSale){
        var product = productRepository.findById(requestSale.id_product()).orElseThrow();

        int discount = requestSale.amount() /10;
        double totalPrice = requestSale.amount() * product.getPrice();

        double totalDiscount = discount * 0.05;
        return totalPrice - (totalPrice * totalDiscount);
    }

    public void updateProduct(RequestSale requestSale){
        var product = productRepository.findById(requestSale.id_product()).orElseThrow();

        if(product.getQuantity() > 0) product.setQuantity(product.getQuantity() - requestSale.amount());

        productRepository.save(product);
    }

    public boolean SaleValid(RequestSale requestSale) {

        var product = productRepository.findById(requestSale.id_product()).orElse(null);

        return requestSale.amount() > 0 && product != null
                && product.getQuantity() > 0
                && requestSale.amount() <= product.getQuantity();
    }

    public List<ResponseSale> getAllSales(){
        var sales = salesRepository.findAll();

        return sales
                .stream()
                .map(sale -> new ResponseSale(sale.getId_product(), sale.getAmount(),sale.getValueFinal(), sale.getDate()))
                .collect(Collectors.toList());

    }
}
