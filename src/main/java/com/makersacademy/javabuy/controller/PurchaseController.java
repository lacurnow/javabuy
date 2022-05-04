package com.makersacademy.javabuy.controller;

import com.makersacademy.javabuy.model.Product;
import com.makersacademy.javabuy.repository.ProductsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PurchaseController {
  @Autowired
  ProductsRepository productsRepository;

  @GetMapping("/purchase/{id}")
    public String makePurchase(@PathVariable("id") Long id, Model model) {
      Product product = productsRepository.findProductById(id);
      model.addAttribute("product", product);
      return "/purchase/make_purchase";
    }
    
  @PostMapping("/purchase/{id}")
    public String purchaseConfirmation(@PathVariable("id") Long id, Model model) {
      Product product = productsRepository.findProductById(id);
      model.addAttribute("product", product);
      product.setAsSold();
      productsRepository.save(product);
      return "/purchase/confirmation";
    }
}
