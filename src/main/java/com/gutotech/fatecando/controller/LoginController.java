package com.gutotech.fatecando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gutotech.fatecando.model.User;
import com.gutotech.fatecando.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping("login")
	public String initLoginForm(Model model) {
		model.addAttribute("user", new User());
		return "users/login";
	}

	@PostMapping("registration")
	public String proccessRegistrationForm(@ModelAttribute User user, Model model) {
		userService.register(user);
		return "redirect:/login";
	}

}
