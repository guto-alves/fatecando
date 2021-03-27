package com.gutotech.fatecando.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.fatecando.model.Question;
import com.gutotech.fatecando.service.DisciplineService;
import com.gutotech.fatecando.service.QuestionService;
import com.gutotech.fatecando.service.TopicService;

@Controller
@RequestMapping("admin/questions")
public class QuestionAdminController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private TopicService topicService;

	@Autowired
	private DisciplineService disciplineService;

	@GetMapping
	public String showAllQuestions(Model model) {
		model.addAttribute("questions", questionService.findAll());
		return "admin/questions";
	}

	@GetMapping("{id}")
	public String initUpdateForm(@PathVariable Long id, Model model) {
		model.addAttribute("question", questionService.findById(id));
		model.addAttribute("topics", topicService.findAll());
		model.addAttribute("disciplines", disciplineService.findAllWithTopics());
		return "admin/question-edit";
	}

	@PostMapping("{id}")
	public String processUpdateForm(@Valid Question question, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(question);
			model.addAttribute("topics", topicService.findAll());
			model.addAttribute("disciplines", disciplineService.findAllWithTopics());
			return "admin/question-edit";
		}

		questionService.update(question);

		redirectAttributes.addFlashAttribute("message",
				String.format("A pergunta #%d foi atualizada com sucesso.", question.getId()));

		return "redirect:/admin/questions";
	}

}
