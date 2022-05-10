package com.makersacademy.javabuy.repository;

import java.util.List;

import com.makersacademy.javabuy.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductsRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%"
    + " OR p.name LIKE %?1%"
    + " OR p.description LIKE %?1%"
    + " OR CONCAT(p.price, '') LIKE %?1%")
  public List<Product> search(String keyword);
  
  //For product purchase
  // Product findProductById(Long productid);
  
  Product findProductById(Long productid);
}
