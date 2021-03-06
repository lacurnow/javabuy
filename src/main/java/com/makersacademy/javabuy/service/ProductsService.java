package com.makersacademy.javabuy.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.makersacademy.javabuy.repository.ProductsRepository;
import com.makersacademy.javabuy.model.Product;

@Service
@Transactional
public class ProductsService {
    @Autowired
    private ProductsRepository repo;

    public List<Product> listAll() {
        return repo.findAll();
    }
     
    public void save(Product product) {
        repo.save(product);
    }
     
    public Product get(long id) {
        return repo.findById(id).get();
    }
     
    public void delete(long id) {
        repo.deleteById(id);
    }
     
    public List<Product> listAll(String keyword) {
        if (keyword != null) {
            return repo.findBykeywordIgnoreCase(keyword);
        }
        return repo.findUnsoldProducts();
    }
}