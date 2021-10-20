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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.fatecando.model.Feedback;
import com.gutotech.fatecando.model.Question;
import com.gutotech.fatecando.model.QuestionType;
import com.gutotech.fatecando.service.QuestionService;
import com.gutotech.fatecando.service.SubjectService;
import com.gutotech.fatecando.service.TopicService;
import com.gutotech.fatecando.service.UserService;

@Controller
@RequestMapping("questions")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private TopicService topicService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private UserService userService;

	@GetMapping
	public String showMyQuestions(Model model) {
		model.addAttribute("questions", userService.findMyQuestions());
		return "users/my-questions";
	}

	@GetMapping("{id}")
	public String initUpdateForm(@PathVariable Long id, Model model) {
		model.addAttribute("question", questionService.findById(id));
		model.addAttribute("questionTypes", QuestionType.getAllTypes());
		model.addAttribute("topics", topicService.findApproved());
		model.addAttribute("subjects", subjectService.findAllWithTopics());
		return "users/question-edit";
	}

	@PostMapping("{id}")
	public String processUpdateForm(@Valid Question question, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			question.setTopic(topicService.findById(question.getTopic().getId()));
			model.addAttribute("question", question);
			model.addAttribute("questionTypes", QuestionType.getAllTypes());
			model.addAttribute("topics", topicService.findApproved());
			model.addAttribute("subjects", subjectService.findAllWithTopics());
			return "users/question-edit";
		}

		questionService.update(question);

		redirectAttributes.addFlashAttribute("message",
				String.format("A pergunta #%d foi atualizada com sucesso.", question.getId()));

		return "redirect:/questions";
	}

	@ResponseBody
	@PostMapping("{questionId}/answer/{alternativeId}")
	public Feedback answerQuestion(@PathVariable("questionId") Long questionId,
			@PathVariable("alternativeId") Long alternativeId) {
		return questionService.answer(questionId, alternativeId);
	}

}
