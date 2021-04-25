package com.gutotech.fatecando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gutotech.fatecando.service.SubjectService;

@Controller
public class WelcomeController {

	@Autowired
	private SubjectService subjectService;

	@GetMapping({ "/", "index", "home" })
	public String welcome(Model model) {
		model.addAttribute("disciplines", subjectService.findAll());
		return "index";
	}

}
