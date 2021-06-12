package com.gutotech.fatecando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		model.addAttribute("user", new User());
		return "users/join";
	}

	@PostMapping
	public String processRegistrationForm(@ModelAttribute User user, Model model,
			RedirectAttributes redirectAttributes) {
		userService.register(user);
		redirectAttributes.addFlashAttribute("successMessage",
				"Sua conta foi criada com sucesso! Faça o login e comece seus estudos agora mesmo!");
		return "redirect:/join";
	}

}
