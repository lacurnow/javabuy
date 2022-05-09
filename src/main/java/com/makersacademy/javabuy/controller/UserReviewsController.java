package com.makersacademy.javabuy.controller;

import java.security.Principal;

import com.makersacademy.javabuy.model.User;
import com.makersacademy.javabuy.model.UserReview;
import com.makersacademy.javabuy.repository.UserRepository;
import com.makersacademy.javabuy.repository.UserReviewsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserReviewsController {

  @Autowired
  UserReviewsRepository userReviewsRepository;

  @Autowired
  UserRepository userRepository;

  private User getSignedInUser(Principal principal) {
    String username = principal.getName();
    User user = userRepository.findByUsername(username);
    return user;
  }

  @PostMapping("/userreviews/{userid}")
  public RedirectView submitUserReview(@PathVariable ("userid") Long userid, @ModelAttribute UserReview userReview, Principal principal) {
    User user = userRepository.findById(userid).get();
    User signedInUser = getSignedInUser(principal);
    userReview.setUser(user);
    userReview.setAuthor(signedInUser);
    userReview.generateTimestamp();
    userReviewsRepository.save(userReview);
    return new RedirectView("/users/{userid}");
  }
}