package com.gutotech.fatecando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gutotech.fatecando.service.RewardService;

@Controller
public class RewardController {

	@Autowired
	private RewardService rewardService;

	@GetMapping("rewards")
	public String showRewardsPage(Model model) {
		model.addAttribute("rewards", rewardService.findAll());
		return "users/rewards";
	}

}
