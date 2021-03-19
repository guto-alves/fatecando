package com.gutotech.fatecando.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.fatecando.model.User;
import com.gutotech.fatecando.service.UserService;

@Controller
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService userService;

	@ModelAttribute("user")
	public User getUser() {
		return userService.findCurrentUser();
	}

	@GetMapping("edit-profile")
	public String initUpdateForm() {
		return "users/edit-profile";
	}

	@PostMapping("edit-profile")
	public String processUpdateForm(@Valid User user, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(user);
			return "users/edit-profile";
		}

		System.out.println(user.getId());

		userService.update(user);

		redirectAttributes.addFlashAttribute("message", "Perfil atualizado com sucesso");

		return "redirect:/users/edit-profile";
	}

}
