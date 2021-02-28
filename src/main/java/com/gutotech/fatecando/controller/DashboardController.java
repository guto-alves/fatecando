package com.gutotech.fatecando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gutotech.fatecando.service.CourseService;
import com.gutotech.fatecando.service.DisciplineService;

@Controller
@RequestMapping("dashboard")
public class DashboardController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private DisciplineService disciplineService;

	@GetMapping
	public String home(Model model) {
		model.addAttribute("courses", courseService.findAll());
		model.addAttribute("disciplines", disciplineService.findAll());
		return "dashboard";
	}

}
