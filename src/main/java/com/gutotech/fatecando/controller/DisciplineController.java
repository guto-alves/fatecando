package com.gutotech.fatecando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gutotech.fatecando.model.Discipline;
import com.gutotech.fatecando.service.DisciplineService;

@Controller
@RequestMapping("disciplines/{disciplineId}")
public class DisciplineController {

	@Autowired
	private DisciplineService disciplineService;

	@ModelAttribute("discipline")
	public Discipline getDiscipline(@PathVariable("disciplineId") Long id) {
		return disciplineService.findById(id);
	}

	@GetMapping
	public String showDiscipline(Discipline discipline,
			@RequestParam(value = "page", required = false) String page, Model model) {
		if (page == null) {
			page = "topics";
		}

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

	@PostMapping("like")
	public ResponseEntity<Void> toggleLike(Discipline discipline) {
		disciplineService.toggleLike(discipline);;
		return ResponseEntity.ok().build();
	}

}
