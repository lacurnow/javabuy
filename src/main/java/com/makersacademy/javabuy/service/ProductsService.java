package com.makersacademy.javabuy.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.makersacademy.javabuy.repository.ProductsRepository;
import com.makersacademy.javabuy.model.Product;

@Service
public class ProductsService {
    @Autowired
    private ProductsRepository repo;
     
    public List<Product> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }
     
}