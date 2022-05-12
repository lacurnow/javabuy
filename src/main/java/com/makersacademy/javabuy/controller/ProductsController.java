package com.makersacademy.javabuy.controller;

// import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.makersacademy.javabuy.model.FavouriteItems;
import com.makersacademy.javabuy.model.Message;
import com.makersacademy.javabuy.service.ProductsService;

import com.makersacademy.javabuy.model.Product;
import com.makersacademy.javabuy.model.Review;
import com.makersacademy.javabuy.model.User;
import com.makersacademy.javabuy.repository.FavouriteItemsRepository;
import com.makersacademy.javabuy.repository.ProductsRepository;
import com.makersacademy.javabuy.repository.ReviewsRepository;
import com.makersacademy.javabuy.repository.UserRepository;

@Controller
public class ProductsController {

    @Autowired
    ProductsRepository repository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReviewsRepository reviewsRepository;

    @Autowired
    private ProductsService service;

    @Autowired
    private FavouriteItemsRepository favouriteItemsRepository;

    @GetMapping("/products")
    public String index(Model model, Principal principal) {
        Iterable<Product> products = repository.findUnsoldProducts();
        User user = getLoggedInUser(principal, userRepository);
        model.addAttribute("user", user);
        model.addAttribute("products", products);
        model.addAttribute("product", new Product());
        return "products/index";
    }

    @GetMapping("/products/{id}")
    public String viewProduct(@PathVariable ("id") Long id, Model model, Principal principal) {
        Product product = repository.findById(id).get();
        // Iterable<Review> review = reviewsRepository.findAllById(id);
        User user = getLoggedInUser(principal, userRepository);
        model.addAttribute("message", new Message());
        model.addAttribute("product", product);
        model.addAttribute("user", user);
        model.addAttribute("review", new Review());
        return "products/product";
    }

    public static User getLoggedInUser(Principal principal, UserRepository userRepository) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        return user;
        
    }

    @PostMapping("/products")
    public RedirectView create(@ModelAttribute Product product, Principal principal) {
        product.setUser(getLoggedInUser(principal, userRepository));
        repository.save(product);
        return new RedirectView("/products");
    }

    @GetMapping("/addproducts")
    public String addProducts(Model model) {
        model.addAttribute("product", new Product());
        return "products/addProducts";
    }

    @GetMapping("/payment")
    public String index() {
        return "payment/index";
    }

    @GetMapping("/products/search")
    public String viewSearch(Model model, @Param("keyword") String keyword) {
        List<Product> listProducts = service.listAll(keyword);
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("keyword", keyword);
         
        return "/products/search";
    }
     
    @PostMapping("/productreviews")
    public RedirectView addReview(@RequestParam Long id, @RequestParam Long userId, @ModelAttribute Review review) {
      Optional<Product> product = repository.findById(id);
      Optional<User> user = userRepository.findById(userId);
      if (user.isPresent() && product.isPresent()) {
      review.setUser(user.get());
      review.setProduct(product.get());
      reviewsRepository.save(review);
      }
    //   review = new Review();
      return new RedirectView(String.format("/products/%o",id));
    }


    @PostMapping("favourites/{id}")
    public RedirectView addToFavourites(@PathVariable ("id") Long id, @ModelAttribute FavouriteItems favouriteItems, Model model, Principal principal, 
            RedirectAttributes redirectAttributes) {
        Optional<Product> product = repository.findById(id);
        User loggedinUser = getLoggedInUser(principal, userRepository);
        Long userId = loggedinUser.getId();
        Optional<User> person = userRepository.findById(userId);
        favouriteItems.setUser(person.get());
        favouriteItems.setProduct(product.get());
        favouriteItems.setFavourite();
        favouriteItemsRepository.save(favouriteItems);
        redirectAttributes.addFlashAttribute("message", String.format("Added to favourites: %s", product.get().getName()));
        return new RedirectView(String.format("/products"));
        }
    }



