package com.makersacademy.javabuy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.makersacademy.javabuy.model.Product;
import com.makersacademy.javabuy.repository.ProductsRepository;
import com.makersacademy.javabuy.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductsRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductsRepository productRepository) {
        this.productRepository = productRepository;
    }

    // @Override
    // public Page<Product> findAllProductsPageable(Pageable pageable) {
    //     return productRepository.findAll(pageable);
    // }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findProductById(id);
    }
}