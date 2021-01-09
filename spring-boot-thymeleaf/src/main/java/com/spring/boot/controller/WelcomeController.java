package com.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	
	@GetMapping("/")
	public String getWelcomePage() {
		return "/wellcome";
	}

}
