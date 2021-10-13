package com.gutotech.fatecando.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.fatecando.model.Question;
import com.gutotech.fatecando.model.Topic;
import com.gutotech.fatecando.service.QuestionService;
import com.gutotech.fatecando.service.SubjectService;
import com.gutotech.fatecando.service.TopicService;

@Controller
@RequestMapping("admin/questions")
public class QuestionAdminController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private TopicService topicService;

	@Autowired
	private SubjectService subjectService;

	@GetMapping
	public String showAllQuestions(@RequestParam(name = "topic", required = false) Long topicId, Model model) {
		if (topicId != null) {
			model.addAttribute("questions", topicService.findQuestions(new Topic(topicId)));
		} else {
			model.addAttribute("questions", questionService.findAll());
		}
		return "admin/questions";
	}

	@GetMapping("{id}")
	public String initUpdateForm(@PathVariable Long id, Model model) {
		model.addAttribute("question", questionService.findById(id));
		model.addAttribute("topics", topicService.findApproved());
		model.addAttribute("subjects", subjectService.findAllWithTopics());
		return "admin/question-edit";
	}

	@PostMapping("{id}")
	public String processUpdateForm(@Valid Question question, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(question);
			model.addAttribute("topics", topicService.findApproved());
			model.addAttribute("subjects", subjectService.findAllWithTopics());
			return "admin/question-edit";
		}

		questionService.update(question);

		redirectAttributes.addFlashAttribute("message",
				String.format("A Questão #%d foi atualizada com sucesso.", question.getId()));

		return "redirect:/admin/questions";
	}

	@PostMapping("{id}/delete")
	public String deleteQuestion(@PathVariable Long id, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		 questionService.delete(id);

		redirectAttributes.addFlashAttribute("message", String.format("A Questão #%d foi excluída com sucesso.", id));
	
		return "redirect:" + request.getHeader("Referer");
	}

}
