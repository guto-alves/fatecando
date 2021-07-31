package com.gutotech.fatecando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gutotech.fatecando.service.UserService;

@Controller
public class RewardController {
	
	@Autowired
	private UserService userService;

	@GetMapping("rewards")
	public String showRewardsPage(Model model) {
		model.addAttribute("rewards", userService.findMyRewards());
		return "users/rewards";
	}

}
