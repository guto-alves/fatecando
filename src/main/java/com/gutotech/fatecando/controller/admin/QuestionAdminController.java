package com.gutotech.fatecando.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.fatecando.model.Question;
import com.gutotech.fatecando.model.QuestionType;
import com.gutotech.fatecando.model.Topic;
import com.gutotech.fatecando.service.QuestionService;
import com.gutotech.fatecando.service.SubjectService;
import com.gutotech.fatecando.service.TopicService;
import com.gutotech.fatecando.service.UserService;

@Controller
@RequestMapping("admin/questions")
public class QuestionAdminController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private TopicService topicService;

	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private UserService userService;

	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
		CustomCollectionEditor topicsEditor = new CustomCollectionEditor(List.class) {
			@Override
			protected Object convertElement(Object element) {
				if (element instanceof String) {
					String name = (String) element;
					QuestionType type = new QuestionType(name);
					return type;
				}
				throw new RuntimeException("Invalid element");
			}
		};

		binder.registerCustomEditor(List.class, "questionTypes", topicsEditor);
	}

	@GetMapping
	public String showAllQuestions(@RequestParam(name = "topic", required = false) Long topicId, Model model) {
		if (topicId != null) {
			Topic topic = topicService.findById(topicId);
			model.addAttribute("topic", topic);
			model.addAttribute("questions", topicService.findQuestions(topic));
		} else {
			model.addAttribute("questions", questionService.findAll());
		}
		return "admin/questions";
	}

	@GetMapping("{id}")
	public String initUpdateForm(@PathVariable Long id, Model model) {
		Question question = questionService.findById(id);
		model.addAttribute("question", question);
		model.addAttribute("questionTypes", QuestionType.getAllTypes());
		model.addAttribute("topics", topicService.findApproved());
		model.addAttribute("subjects", subjectService.findAllWithTopics());
		return "admin/question-edit";
	}

	@PostMapping("{id}")
	public String processUpdateForm(@Valid Question question, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			question.setTopic(topicService.findById(question.getTopic().getId()));
			question.setUser(userService.findById(question.getUser().getId()));
			model.addAttribute("question", question);
			model.addAttribute("questionTypes", QuestionType.getAllTypes());
			model.addAttribute("topics", topicService.findApproved());
			model.addAttribute("subjects", subjectService.findAllWithTopics());
			return "admin/question-edit";
		}

		questionService.update(question);

		redirectAttributes.addFlashAttribute("message",
				String.format("A Questão #%d foi atualizada com sucesso.", question.getId()));

		return "redirect:/admin/questions?topic=" + question.getTopic().getId();
	}

	@PostMapping("{id}/delete")
	public String deleteQuestion(@PathVariable Long id, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		questionService.delete(id);

		redirectAttributes.addFlashAttribute("message", String.format("A Questão #%d foi excluída com sucesso.", id));

		return "redirect:" + request.getHeader("Referer");
	}

}
