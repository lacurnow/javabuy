package com.makersacademy.javabuy.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.transaction.Transactional;
import com.makersacademy.javabuy.service.ShoppingCartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.makersacademy.javabuy.model.Product;
import com.makersacademy.javabuy.repository.ProductsRepository;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.context.annotation.ScopedProxyMode;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

  private final ProductsRepository productsRepository;

  private Map<Product, Integer> productsInCart = new HashMap<>();

  @Autowired
  public ShoppingCartServiceImpl(ProductsRepository productsRepository) {
    this.productsRepository = productsRepository;
  }

  @Override
  public void addProduct(Product product) {
      if (productsInCart.containsKey(product)) {
        productsInCart.replace(product, productsInCart.get(product) + 1);
      } else {
        productsInCart.put(product, 1);
      }
  }

  @Override
  public void removeProduct(Product product) {
    if (productsInCart.containsKey(product)) {
      if (productsInCart.get(product) > 1) {
        productsInCart.replace(product, productsInCart.get(product) -1);
      } else if (productsInCart.get(product) == 1) {
        productsInCart.remove(product);
      }
    }
  }

  /**
   * @return unmodifiable copy of the map
   */
  @Override
  public Map<Product, Integer> getProductsInCart() {
    return Collections.unmodifiableMap(productsInCart);
  }
  
  @Override
  public void checkout() {
    Product product;
    for (Map.Entry<Product, Integer> entry : productsInCart.entrySet()) {
        product = productsRepository.findProductById(product.getId());
    }
    productsRepository.save(productsInCart.keySet());
    productsRepository.flush();
    productsInCart.clear();
  }

  @Override
  public BigDecimal getTotal() {
    return productsInCart.entrySet().stream()
    .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
    .reduce(BigDecimal::add)
    .orElse(BigDecimal.ZERO);
  }
}