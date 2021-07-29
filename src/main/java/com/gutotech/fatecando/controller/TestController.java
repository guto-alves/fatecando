package com.gutotech.fatecando.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gutotech.fatecando.model.Feedback;
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

		return "subjects/test";
	}

	@PostMapping
	public String finishTestForm(Test test, Model model) {
		testService.finish();
		return "redirect:/subjects/" + test.getSubject().getId();
	}
	
	@ResponseBody
	@PostMapping("check")
	public List<Feedback> checkAnswers(@RequestBody List<Long> alternativeIds) {
		return testService.checkAnswers(alternativeIds);
	}
	
}
