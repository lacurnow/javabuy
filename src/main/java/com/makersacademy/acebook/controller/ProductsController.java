package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Product;
import com.makersacademy.acebook.repository.ProductsRepository;
// import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class ProductsController {

    @Autowired
    ProductsRepository repository;

    @GetMapping("/posts")
    public String index(Model model) {
        Iterable<Product> posts = repository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("post", new Product());
        return "posts/index";
    }

    @PostMapping("/posts")
    public RedirectView create(@ModelAttribute Product post) {
        repository.save(post);
        return new RedirectView("/posts");
    }
}
