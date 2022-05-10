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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    Iterable<Message> receivedEnquiries = messagesRepository.findBySellerGroupByEnquirerAndProductAndSeller(user);
    Iterable<Message> sentEnquiries = messagesRepository.findByEnquirerGroupByEnquirerAndProductAndSeller(user);
    model.addAttribute("receivedEnquiries", receivedEnquiries);
    model.addAttribute("sentEnquiries", sentEnquiries);
    model.addAttribute("user", user);
    return "messages/index";
  }

  @GetMapping("/messages/{sellerid}/{enquirerid}")
  public String viewMessages(@PathVariable ("sellerid") Long sellerid, @PathVariable ("enquirerid") Long enquirerid, @RequestParam(required = false) Long productid, Model model, Principal principal) {
    User user = getUser(principal);
    User seller = userRepository.findById(sellerid).get();
    User enquirer = userRepository.findById(enquirerid).get();
    if (productid != null) {
      Product product = productsRepository.findById(productid).get();
      model.addAttribute("product", product);
      Iterable<Message> messages = messagesRepository.findMessagesByProductAndEnquirer(product, enquirer);
      model.addAttribute("messages", messages);
    } else {
      Iterable<Message> messages = messagesRepository.findMessagesBySellerAndEnquirer(seller, enquirer);
      model.addAttribute("messages", messages);
    }
    model.addAttribute("user", user);
    model.addAttribute("seller", seller);
    model.addAttribute("enquirer", enquirer);
    model.addAttribute("message", new Message());
    return "messages/thread";
  }

  @PostMapping("/messages/{sellerid}/{enquirerid}")
  public RedirectView sendMessage(@PathVariable ("sellerid") Long sellerid, @PathVariable ("enquirerid") Long enquirerid, @RequestParam(required = false) Long productid, @ModelAttribute Message message, Principal principal, RedirectAttributes redirectAttributes) {
    User enquirer = userRepository.findById(enquirerid).get();
    User seller = userRepository.findById(sellerid).get();
    message.setSeller(seller);
    message.setEnquirer(enquirer);
    message.setSender(getUser(principal));
    message.generateTimestamp();
    if (productid != null) {
      Product product = productsRepository.findById(productid).get();
      message.setProduct(product);
      messagesRepository.save(message);
      redirectAttributes.addAttribute("productid", productid);
      return new RedirectView("/messages/{sellerid}/{enquirerid}?productid={productid}");
    } else {
      messagesRepository.save(message);
      return new RedirectView("/messages/{sellerid}/{enquirerid}");
    }
  }
}