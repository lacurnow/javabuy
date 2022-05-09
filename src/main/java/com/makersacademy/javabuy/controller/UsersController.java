package com.makersacademy.javabuy.controller;

import java.security.Principal;

import com.makersacademy.javabuy.model.Authority;
import com.makersacademy.javabuy.model.Product;
import com.makersacademy.javabuy.model.User;
import com.makersacademy.javabuy.repository.AuthoritiesRepository;
import com.makersacademy.javabuy.repository.ProductsRepository;
import com.makersacademy.javabuy.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UsersController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;

    @Autowired
    ProductsRepository productRepository;

    private User getUser(Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        return user;
    }

    @GetMapping("/users/new")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping("/users")
    public RedirectView signup(@ModelAttribute User user) {
        userRepository.save(user);
        Authority authority = new Authority(user.getUsername(), "ROLE_USER");
        authoritiesRepository.save(authority);
        return new RedirectView("/users");
    }

    @GetMapping("/users")
    public RedirectView login() {
    return new RedirectView("/login");
    }

    @GetMapping("/account")
    public String addProfilePhoto(Model model) {
        model.addAttribute("user", new User());
        return "users/accountPage";
    }

    @GetMapping("/accountdetails")
    public String editAccountDetails() {
        return "users/accountDetails";
    }

    @GetMapping("/reviews")
    public String seeBuyerReviews() {
        return "users/reviews";
    }

    @GetMapping("/solditems")
    public String seeSoldItems(Model model, Principal principal) {
        User user = getUser(principal);
        Iterable<Product> soldProducts = productRepository.findBySoldTrue(user);
        model.addAttribute("soldProducts", soldProducts);
        model.addAttribute("product", new Product());
        return "users/soldItems";
    }

    @GetMapping("/listeditems")
    public String seeListedItems(Model model, Principal principal) {
        User user = getUser(principal);
        Iterable<Product> listedProducts = productRepository.findListedProductsByUser(user);
        model.addAttribute("listedProducts", listedProducts);
        model.addAttribute("product", new Product());
        return "users/listedItems";
    }
}
