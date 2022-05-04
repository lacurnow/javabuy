package com.makersacademy.javabuy.repository;

import java.util.List;

import com.makersacademy.javabuy.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductsRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%"
    + " OR p.name LIKE %?1%"
    + " OR p.description LIKE %?1%"
    + " OR CONCAT(p.price, '') LIKE %?1%")
public List<Product> search(String keyword);

}
