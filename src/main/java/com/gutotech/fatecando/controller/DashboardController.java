package com.gutotech.fatecando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gutotech.fatecando.service.SubjectService;
import com.gutotech.fatecando.service.UserService;

@Controller
@RequestMapping("dashboard")
public class DashboardController {

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private UserService userService;

	@GetMapping
	public String home(Model model) {
		model.addAttribute("subjects", subjectService.findAll());
		model.addAttribute("recentSubjects", userService.findSubjectsAccessed());
		return "subjects/subjects";
	}

}
