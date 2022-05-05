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

  @GetMapping("productreviews")
  public String listReviews(@RequestParam Long productId, Model model) {
    Product product = productsRepository.findProductById(productId);
    model.addAttribute("product", product);
    product.getReviews();
    return "/products/product_reviews";
  }

  @PostMapping("/productreviews")
  public RedirectView addReview(@RequestParam Long productId, @RequestParam Long userId, Model model, @ModelAttribute Review review) {
    Product product = productsRepository.findProductById(productId);
    model.addAttribute("product", product);
    User user = userRepository.findById(userId);
    model.addAttribute("user", user);
    // Review review = new Review();
    model.addAttribute("review", review);
    review.setUser(user);
    review.setProduct(product);
    reviewsRepository.save(review);
    return new RedirectView("/productreviews/{id}");
  }
}
