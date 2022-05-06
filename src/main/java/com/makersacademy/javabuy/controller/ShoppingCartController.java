package com.makersacademy.javabuy.controller;

import com.makersacademy.javabuy.model.Product;
import com.makersacademy.javabuy.service.ProductService;
import com.makersacademy.javabuy.service.ShoppingCartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShoppingCartController {

  private final ShoppingCartService shoppingCartService;

  private final ProductService productService;

  @Autowired
  public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService) {
    this.shoppingCartService = shoppingCartService;
    this.productService = productService;
  }

  @GetMapping("/shoppingCart")
  public ModelAndView shoppingCart() {
    ModelAndView modelAndView = new ModelAndView("/shopping_cart");
    modelAndView.addObject("products", shoppingCartService.getProductsInCart());
    modelAndView.addObject("total", shoppingCartService.getTotal().toString());
    return modelAndView;
  }

  @GetMapping("/shoppingCart/addProduct/{id}")
  public ModelAndView addProductToCart(@PathVariable("id") Long id) {
    Product product = productService.findProductById(id);
    shoppingCartService.addProduct(product);
    return shoppingCart();
  }

  @GetMapping("/shoppingCart/removeProduct/{id}")
  public ModelAndView removeProductFromCart(@PathVariable("id") Long id) {
    Product product = productService.findProductById(id);
    shoppingCartService.removeProduct(product);
    return shoppingCart();
  }

  @GetMapping("/shoppingCart/checkout")
public ModelAndView checkout() {
    shoppingCartService.checkout();
    return shoppingCart();
  }
}
