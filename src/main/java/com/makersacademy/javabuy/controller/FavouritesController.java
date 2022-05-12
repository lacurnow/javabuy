package com.makersacademy.javabuy.controller;

// import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
public class FavouritesController {

    @Autowired
    private FavouriteItemsRepository favouriteItemsRepository;
    @Autowired
    UserRepository userRepository;


    public static Long getLoggedInUser(Principal principal, UserRepository userRepository) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
       return user.getId();
        
    }

    @GetMapping("/favourites")
    public String index(Model model, Principal principal) {
        Long user = getLoggedInUser(principal, userRepository);
        Iterable<FavouriteItems> favourites = favouriteItemsRepository.findByuser_id(user);
        model.addAttribute("favourites", favourites);
        return "favourites/index";
    }

    @PostMapping("/favourites/remove/{id}")
    public RedirectView removeFromfavourites(@PathVariable ("id") Long id) {
       favouriteItemsRepository.deleteByproduct_id(id);
       return new RedirectView(String.format("/favourites"));
    }
    
}
