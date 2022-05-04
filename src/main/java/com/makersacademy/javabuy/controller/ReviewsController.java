package com.makersacademy.javabuy.controller;

import com.makersacademy.javabuy.model.Product;
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
}
