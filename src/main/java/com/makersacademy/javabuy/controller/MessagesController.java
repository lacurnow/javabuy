package com.makersacademy.javabuy.controller;

import java.security.Principal;

import com.makersacademy.javabuy.model.Message;
import com.makersacademy.javabuy.model.Product;
import com.makersacademy.javabuy.model.User;
import com.makersacademy.javabuy.repository.MessagesRepository;
import com.makersacademy.javabuy.repository.ProductsRepository;
import com.makersacademy.javabuy.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MessagesController {

  @Autowired
  MessagesRepository messagesRepository;
  @Autowired
  ProductsRepository productsRepository;
  @Autowired
  UserRepository userRepository;

  private User getUser(Principal principal) {
    String username = principal.getName();
    User user = userRepository.findByUsername(username);
    return user;
  }

  @PostMapping("/messages/{productid}")
  public RedirectView sendMessage(@PathVariable ("productid") Long productid, @ModelAttribute Message message, Principal principal) {
    Product product = productsRepository.findById(productid).get();
    message.setProduct(product);
    message.setEnquirer(getUser(principal));
    message.setSender(getUser(principal));
    message.generateTimestamp();
    if (message.getContent() != "") {
      messagesRepository.save(message);
    }
    return new RedirectView("/products");
  }
}