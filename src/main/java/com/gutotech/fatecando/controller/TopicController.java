package com.gutotech.fatecando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gutotech.fatecando.model.Topic;
import com.gutotech.fatecando.service.TopicService;

@Controller
@RequestMapping("topic")
public class TopicController {

	@Autowired
	private TopicService topicService;

	@GetMapping("{id}")
	public String showDisciplines(@PathVariable("id") Long id, Model model) {
		Topic topic = topicService.findById(id);

		model.addAttribute("topic", topic);

		return "disciplines/topic-details";
	}

}
