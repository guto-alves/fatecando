package com.gutotech.fatecando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gutotech.fatecando.service.UserService;

@Controller
@RequestMapping("ranking")
public class RankingController {

	@Autowired
	private UserService userService;

	@GetMapping
	public String showRanking(Model model) {
		model.addAttribute("users", userService.getRanking());
		return "users/ranking";
	}
}
