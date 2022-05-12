package com.makersacademy.javabuy.controller;

import java.util.Map;

import com.makersacademy.javabuy.model.Product;
import com.makersacademy.javabuy.repository.ProductsRepository;
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
  ProductsRepository productsRepository;

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
    Map<Product, Integer> productsInCart = shoppingCartService.getProductsInCart();
    if(!productsInCart.containsKey(product)) {
      shoppingCartService.addProduct(product);
    }
    return shoppingCart();
  }

  @GetMapping("/shoppingCart/removeProduct/{id}")
  public ModelAndView removeProductFromCart(@PathVariable("id") Long id) {
    Product product = productService.findProductById(id);
    shoppingCartService.removeProduct(product);
    return shoppingCart();
  }

  @GetMapping("/shoppingCart/checkout")
public ModelAndView checkOut() {
    // Open Paypal here in new window?
    ModelAndView modelAndView = new ModelAndView("/payment/index");
    modelAndView.addObject("products", shoppingCartService.getProductsInCart());
    modelAndView.addObject("total", shoppingCartService.getTotal().toString());
    return modelAndView;
  }

  @GetMapping("/refresh")
  public String refreshCart() {
    //For products in hashmap, mark as sold in products repository.
    Map<Product, Integer> productsInCart = shoppingCartService.getProductsInCart();
    for (Product product : productsInCart.keySet()) {
      productsRepository.save(product);
    }
    shoppingCartService.checkout();
    return "/continueShopping";
  }
}
