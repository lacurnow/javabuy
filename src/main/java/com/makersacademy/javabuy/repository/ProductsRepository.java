package com.makersacademy.javabuy.repository;

import com.makersacademy.javabuy.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public interface ProductsRepository extends CrudRepository<Product, Long> {
  Product findProductById(Long productid);
}
