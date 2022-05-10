package com.makersacademy.javabuy.service;

import java.math.BigDecimal;
import java.util.Map;
import com.makersacademy.javabuy.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface ShoppingCartService {

    void addProduct(Product product);

    void removeProduct(Product product);

    Map<Product, Integer> getProductsInCart();

    void checkout(); 
    // throws NotEnoughProductsInStockException;

    BigDecimal getTotal();
}
