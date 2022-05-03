package com.makersacademy.javabuy.controller;

// import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

import com.makersacademy.javabuy.model.Product;
import com.makersacademy.javabuy.repository.ProductsRepository;

@Controller
public class ProductsController {

    @Autowired
    ProductsRepository repository;

    @GetMapping("/products")
    public String index(Model model) {
        Iterable<Product> products = repository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("product", new Product());
        return "products/index";
    }

    @PostMapping("/products")
    public RedirectView create(@ModelAttribute Product product) {
        repository.save(product);
        return new RedirectView("/products");
    }
}
