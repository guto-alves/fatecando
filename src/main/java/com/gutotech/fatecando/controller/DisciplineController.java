package com.gutotech.fatecando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gutotech.fatecando.model.Discipline;
import com.gutotech.fatecando.service.DisciplineService;

@Controller
@RequestMapping("disciplines")
public class DisciplineController {

	@Autowired
	private DisciplineService disciplineService;

	@GetMapping
	public String home(Model model) {
		model.addAttribute("disciplines", disciplineService.findAll());
		return "disciplines/disciplines";
	}

	@GetMapping("{id}")
	public String showDiscipline(@PathVariable("id") Long id,
			@RequestParam(value = "page", required = false) String page, Model model) {
		if (page == null) {
			page = "topics";
		}

		Discipline discipline = disciplineService.findById(id);

		model.addAttribute("discipline", discipline);

		model.addAttribute("page", page);

		switch (page) {
		case "topics":
			model.addAttribute("topics", disciplineService.findAllTopicsByDiscipline(discipline));
			break;
		case "tests":
			// add tests
			break;
		case "forum":
			model.addAttribute("forumTopics", disciplineService.findAllForumTopicsByDiscipline(discipline));
			break;
		default:
			model.addAttribute("page", "forum");
			model.addAttribute("topics", disciplineService.findAllTopicsByDiscipline(discipline));
			break;
		}

		return "disciplines/discipline-details";
	}

}
