package com.gutotech.fatecando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gutotech.fatecando.model.Topic;
import com.gutotech.fatecando.model.UploadStatus;
import com.gutotech.fatecando.service.DisciplineService;
import com.gutotech.fatecando.service.QuestionService;
import com.gutotech.fatecando.service.TopicService;

@Controller
@RequestMapping("topic")
public class TopicController {

	@Autowired
	private TopicService topicService;

	@Autowired
	private DisciplineService disciplineService;

	@Autowired
	private QuestionService questionService;

	@GetMapping("{id}")
	public String showTopic(@PathVariable("id") Long id, Model model) {
		Topic topic = topicService.findById(id);

		model.addAttribute("topic", topic);
		model.addAttribute("questions", questionService.findAllByTopic(topic));

		return "disciplines/topic-details";
	}

	@GetMapping
	public String initCreationForm(Model model) {
		model.addAttribute("topic", new Topic());
		model.addAttribute("disciplines", disciplineService.findAll());
		return "admin/topic-edit";
	}

	@PostMapping
	public String processCreationForm(Topic topic, Model model, BindingResult bindingResult) {
		topic.setStatus(UploadStatus.WAITING_FOR_RESPONSE);
		topic.setRequired(false);
		topicService.save(topic);
		return "redirect:/dashboard";
	}

}
