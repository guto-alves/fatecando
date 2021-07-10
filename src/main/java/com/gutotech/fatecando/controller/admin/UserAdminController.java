package com.gutotech.fatecando.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gutotech.fatecando.model.User;
import com.gutotech.fatecando.service.UserService;

@Controller
@RequestMapping("admin/users")
public class UserAdminController {

	@Autowired
	private UserService userService;

	@ModelAttribute("users")
	public List<User> getUsers() {
		return userService.findAll();
	}

	@GetMapping
	public String showUsersPage() {
		return "admin/users";
	}
}
