package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public interface ProductsRepository extends CrudRepository<Product, Long> {

}
