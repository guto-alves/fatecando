package com.gutotech.fatecando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gutotech.fatecando.model.Topic;
import com.gutotech.fatecando.service.QuestionService;
import com.gutotech.fatecando.service.TopicService;

@Controller
@RequestMapping("topic/{topicId}")
public class TopicController {

	@Autowired
	private TopicService topicService;

	@Autowired
	private QuestionService questionService;

	@ModelAttribute("topic")
	public Topic getTopic(@PathVariable("topicId") Long topicId) {
		return topicService.findById(topicId);
	}

	@InitBinder("topic")
	public void initDisciplineBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping
	public String showTopic(Topic topic, Model model) {
		model.addAttribute("questions", questionService.findAllByTopic(topic));
		return "disciplines/topic-details";
	}

	@PostMapping("finished")
	public ResponseEntity<Void> toggleFinished(Topic topic) {
		topicService.toggleFinished(topic);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("like")
	public ResponseEntity<Void> toggleLike(Topic topic) {
		topicService.toggleLike(topic);
		return ResponseEntity.noContent().build();
	}

//	@GetMapping
//	public String initCreationForm(Model model) {
//		model.addAttribute("topic", new Topic());
//		model.addAttribute("disciplines", disciplineService.findAll());
//		return "admin/topic-edit";
//	}
//
//	@PostMapping
//	public String processCreationForm(Topic topic, Model model, BindingResult bindingResult) {
//		topic.setStatus(UploadStatus.WAITING_FOR_RESPONSE);
//		topic.setRequired(false);
//		topicService.save(topic);
//		return "redirect:/dashboard";
//	}
}
