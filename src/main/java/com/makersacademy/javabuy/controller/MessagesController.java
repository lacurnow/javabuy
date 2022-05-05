package com.makersacademy.javabuy.controller;

import java.security.Principal;
import java.util.List;

import com.makersacademy.javabuy.model.Message;
import com.makersacademy.javabuy.model.Product;
import com.makersacademy.javabuy.model.User;
import com.makersacademy.javabuy.repository.MessagesRepository;
import com.makersacademy.javabuy.repository.ProductsRepository;
import com.makersacademy.javabuy.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

  @GetMapping("/messages")
  public String index(Model model, Principal principal) {
    User user = getUser(principal);
    Iterable<Message> receivedEnquiries = messagesRepository.findBySellerGroupByEnquirerAndProduct(user);
    Iterable<Message> sentEnquiries = messagesRepository.findByEnquirerGroupByEnquirerAndProduct(user);
    model.addAttribute("receivedEnquiries", receivedEnquiries);
    model.addAttribute("sentEnquiries", sentEnquiries);
    return "messages/index";
  }

  // @GetMapping("/messages/{productid}")
  // public String viewMessages(@PathVariable ("productid") Long productid, Model model, Principal principal) {
  //   Product product = productsRepository.findById(productid).get();
  //   Iterable<User> enquirers = messagesRepository.findEnquirersByProduct(product);
  //   model.addAttribute("enquirers", enquirers);
  //   return "messages/test";
  // }


  @GetMapping("/messages/{productid}/{enquirerid}")
  public String viewMessages(@PathVariable ("productid") Long productid, @PathVariable ("enquirerid") Long enquirerid, Model model, Principal principal) {
    Product product = productsRepository.findById(productid).get();
    User user = getUser(principal);
    User enquirer = userRepository.findById(enquirerid).get();
    Iterable<Message> messages = messagesRepository.findMessagesByProductAndEnquirer(product, enquirer);
      model.addAttribute("user", user);
      model.addAttribute("messages", messages);
      model.addAttribute("product", product);
      model.addAttribute("enquirer", enquirer);
      model.addAttribute("message", new Message());
      return "messages/thread";


  }

  @PostMapping("/messages/{productid}")
  public RedirectView sendMessage(@PathVariable ("productid") Long productid, @ModelAttribute Message message, Principal principal) {
    Product product = productsRepository.findById(productid).get();
    message.setProduct(product);
    message.setSeller(product.getUser());
    message.setEnquirer(getUser(principal));
    message.setSender(getUser(principal));
    message.generateTimestamp();
    if (message.getContent() != "") {
      messagesRepository.save(message);
    }
    return new RedirectView("/messages");
  }

  @PostMapping("/messages/{productid}/{enquirerid}")
  public RedirectView sendReply(@PathVariable ("productid") Long productid, @PathVariable ("enquirerid") Long enquirerid, @ModelAttribute Message message, Principal principal) {
    Product product = productsRepository.findById(productid).get();
    User enquirer = userRepository.findById(enquirerid).get();
    message.setProduct(product);
    message.setSeller(product.getUser());
    message.setEnquirer(enquirer);
    message.setSender(getUser(principal));
    message.generateTimestamp();
    if (message.getContent() != "") {
      messagesRepository.save(message);
    }
    return new RedirectView("/messages/{productid}/{enquirerid}");
  }
}