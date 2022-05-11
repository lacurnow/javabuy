package com.makersacademy.javabuy.repository;

import java.util.List;

import com.makersacademy.javabuy.model.Product;
import com.makersacademy.javabuy.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductsRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%"
    + " OR p.name LIKE %?1%"
    + " OR p.description LIKE %?1%"
    + " OR CONCAT(p.price, '') LIKE %?1%")
  public List<Product> search(String keyword);
  
  //For product purchase
  // Product findProductById(Long productid);
  
  Product findProductById(Long productid);

  // For finding sold items
  @Query("SELECT p FROM Product p WHERE p.sold = true AND p.user = ?1")
  public Iterable<Product> findBySoldTrue(User user);

  @Query("SELECT p FROM Product p WHERE p.user = ?1")
  public Iterable<Product> findListedProductsByUser(User user);

  @Query("SELECT p FROM Product p WHERE p.sold = false OR p.sold = null")
  public Iterable<Product> findUnsoldProducts();

  @Query(nativeQuery = true, value = "SELECT * FROM products WHERE LENGTH(photo) > 0 AND sold IS NULL OR sold = false ORDER BY RANDOM() LIMIT 3")
  public Iterable<Product> findRandomProducts();
}
