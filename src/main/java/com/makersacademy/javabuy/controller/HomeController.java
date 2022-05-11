package com.makersacademy.javabuy.controller;

import com.makersacademy.javabuy.model.Product;
import com.makersacademy.javabuy.repository.ProductsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

	@Autowired
	ProductsRepository productsRepository;

	@RequestMapping(value = "/")
	public String homePage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth instanceof AnonymousAuthenticationToken) {
			Iterable<Product> products = productsRepository.findRandomProducts();
			model.addAttribute("products", products);
			return "homePage";
		} else {
			return "redirect:/products";
		}	
	}

	public RedirectView index() {
		return new RedirectView("/products");  
	}

	@PostMapping("/contact_page")
	public RedirectView contactForm() {
		return new RedirectView("/contactus");
	}

	@GetMapping("/contact_page")
	public String contactUs() {
		return "contactUs";
	}

	@GetMapping("/contactus")
	public String confirmation() {
		return "contactFormConfirmation";
	}
}
