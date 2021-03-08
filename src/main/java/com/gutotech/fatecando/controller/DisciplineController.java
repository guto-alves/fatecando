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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.fatecando.model.Discipline;
import com.gutotech.fatecando.model.ForumTopic;
import com.gutotech.fatecando.service.DisciplineService;

@Controller
@RequestMapping("disciplines/{disciplineId}")
public class DisciplineController {

	@Autowired
	private DisciplineService disciplineService;

	@ModelAttribute("discipline")
	public Discipline getDiscipline(@PathVariable("disciplineId") Long disciplineId) {
		return disciplineService.findById(disciplineId);
	}

	@GetMapping
	public String showDiscipline(Discipline discipline,
			@RequestParam(value = "page", required = false, defaultValue = "topics") String page, Model model) {
		model.addAttribute("page", page);

		switch (page) {
		case "topics":
			model.addAttribute("topics", disciplineService.findAllTopicsByDiscipline(discipline));
			break;
		case "tests":
			// add tests
			break;
		case "forum":
			model.addAttribute("forumTopic", new ForumTopic());
			model.addAttribute("forumTopics", disciplineService.findAllForumTopicsByDiscipline(discipline));
			break;
		default:
			model.addAttribute("page", "topics");
			model.addAttribute("topics", disciplineService.findAllTopicsByDiscipline(discipline));
			break;
		}

		return "disciplines/discipline-details";
	}

	@PostMapping("like")
	public ResponseEntity<Void> toggleLike(Discipline discipline) {
		disciplineService.toggleLike(discipline);
		return ResponseEntity.ok().build();
	}

	@PostMapping("forum-topic")
	public String processForumTopicCreatitonForm(Discipline discipline,
			@ModelAttribute("forumTopic") ForumTopic forumTopic, RedirectAttributes redirectAttributes, Model model) {
		disciplineService.saveForumTopic(discipline, forumTopic);
		
		redirectAttributes.addFlashAttribute("message", "Tópico para o Fórum da disciplina foi criado com sucesso.");
		
		return "redirect:/disciplines/" + discipline.getId() + "?page=forum";
	}

}
