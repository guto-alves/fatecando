package com.gutotech.fatecando.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gutotech.fatecando.model.Topic;
import com.gutotech.fatecando.service.DisciplineService;
import com.gutotech.fatecando.service.TopicService;

@Controller
@RequestMapping("admin/topics")
public class TopicAdminController {

	@Autowired
	private TopicService topicService;

	@Autowired
	private DisciplineService disciplineService;

	@GetMapping
	public String showAllTopics(Model model) {
		model.addAttribute("topics", topicService.findAll());
		return "admin/topics";
	}

	@GetMapping("{id}")
	public String initUpdateForm(@PathVariable Long id, Model model) {
		Topic topic = topicService.findById(id);
		model.addAttribute("topic", topic);
		model.addAttribute("disciplines", disciplineService.findAll());
		return "admin/topic-edit";
	}

	@PostMapping("{id}")
	public String processUpdateForm(@Valid Topic topic, Model model) {
		topicService.update(topic);
		return "redirect:/admin/topics";
	}

}
