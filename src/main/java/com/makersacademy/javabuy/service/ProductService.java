package com.makersacademy.javabuy.service;

import com.makersacademy.javabuy.model.Product;

import org.springframework.stereotype.Service;

@Service
public interface ProductService {    
    Product findProductById(Long id);
}
