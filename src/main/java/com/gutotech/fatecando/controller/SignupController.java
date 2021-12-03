package com.gutotech.fatecando.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.fatecando.model.User;
import com.gutotech.fatecando.service.UserService;

@Controller
@RequestMapping("join")
public class SignupController {

	@Autowired
	private UserService userService;

	@GetMapping
	public String initRegistrationForm(Model model) {
		if (userService.isLoggedIn()) {
			return "redirect:/dashboard";
		}
		
		model.addAttribute("user", new User());
		return "users/join";
	}

	@PostMapping
	public String processRegistrationForm(@Valid User user, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(user);
			return "users/join";
		}
		userService.register(user);
		redirectAttributes.addFlashAttribute("email", user.getEmail());
		return "redirect:/account";
	}

}
