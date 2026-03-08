package com.leandro.library_manager_JPA.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	// Este método atende a raiz do site (localhost:8080)
	public String Home() {
		return "home"; // Vai procurar por home.html
	}
	
}
