package com.gutotech.fatecando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.fatecando.service.UserService;

@Controller
public class RegistrationController {

	@Autowired
	private UserService userService;

	@GetMapping("registration-confirm")
	public String confirmRegistration(@RequestParam String token, Model model,
			RedirectAttributes redirectAttributes) {
		if (userService.isValidVerificationToken(token)) {
			redirectAttributes.addFlashAttribute("successMessage",
					"Conta confirmada com sucesso! Faça o login e comece seus estudos agora mesmo!");
			return "redirect:/join";
		}

		return "registration-confirm";
	}

	@GetMapping("account")
	public String account(Model model) {
//		if (model.getAttribute("email") == null) {
//			return "redirect:/";
//		}
		return "account";
	}

}
