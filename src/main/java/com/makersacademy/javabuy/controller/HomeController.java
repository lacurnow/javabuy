package com.makersacademy.javabuy.controller;

import org.springframework.stereotype.Controller;
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
}
