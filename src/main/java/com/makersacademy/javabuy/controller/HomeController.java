package com.makersacademy.javabuy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {
	@RequestMapping(value = "/")
	public String homePage() {
		return "homePage";
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
