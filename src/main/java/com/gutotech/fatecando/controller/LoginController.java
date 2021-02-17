package com.gutotech.fatecando.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("login")
	public String initLoginForm() {
		return "students/login";
	}

	@GetMapping("join")
	public String initRegistraionFor() {
		return "students/signup";
	}
}
