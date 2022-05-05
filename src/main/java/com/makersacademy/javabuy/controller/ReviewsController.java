package com.makersacademy.javabuy.controller;

import java.util.Optional;

import com.makersacademy.javabuy.model.Product;
import com.makersacademy.javabuy.model.Review;
import com.makersacademy.javabuy.model.User;
import com.makersacademy.javabuy.repository.ProductsRepository;
import com.makersacademy.javabuy.repository.ReviewsRepository;
import com.makersacademy.javabuy.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

public class ReviewsController {

  @Autowired
  ProductsRepository productsRepository;

  @Autowired
  ReviewsRepository reviewsRepository;

  @Autowired
  UserRepository userRepository;

  // @GetMapping("/productreviews/{productId}")
  // public String listReviews(@PathVariable("productId") Long productId, Model model) {
  //   Product product = productsRepository.findProductById(productId);
  //   model.addAttribute("product", product);
  //   product.getReviews();
  //   return "products/product";
  // }

  // @PostMapping("/productreviews")
  // public RedirectView addReview(@RequestParam Long id, @RequestParam Long userId, @ModelAttribute Review review) {
  //   Optional<Product> product = productsRepository.findById(id);
  //   Optional<User> user = userRepository.findById(userId);
  //   if (user.isPresent() && product.isPresent()) {
  //   review.setUser(user.get());
  //   review.setProduct(product.get());
  //   reviewsRepository.save(review);
  //   }
  //   return new RedirectView("/products/{id}");
  // }
}
