package com.gutotech.fatecando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gutotech.fatecando.model.Test;
import com.gutotech.fatecando.service.TestService;

@Controller
@RequestMapping("test")
public class TestController {

	@Autowired
	private TestService testService;

	@ModelAttribute
	public Test getTest() {
		return testService.find();
	}

	@GetMapping
	public String initTestForm(Test test, Model model) {
		if (test == null) {
			return "redirect:/dashboard";
		}

		return "disciplines/test";
	}

	@PostMapping
	public String finishTestForm(Test test, Model model) {
		testService.finish();
		return "redirect:/disciplines/" + test.getDiscipline().getId();
	}
}
