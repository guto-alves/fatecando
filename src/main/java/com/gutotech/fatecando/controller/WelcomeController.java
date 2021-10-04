package com.gutotech.fatecando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gutotech.fatecando.service.UserService;

@Controller
public class WelcomeController {
	
	@Autowired
	private UserService userService;

	@GetMapping({ "/", "index", "home" })
	public String welcome(Model model) {
		if (userService.isLoggedIn()) {
			return "redirect:/dashboard";
		}
		
		return "index";
	}

}
