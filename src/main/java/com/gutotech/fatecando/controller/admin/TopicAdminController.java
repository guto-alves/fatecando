package com.gutotech.fatecando.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.fatecando.model.Topic;
import com.gutotech.fatecando.service.SubjectService;
import com.gutotech.fatecando.service.TopicService;

@Controller
@RequestMapping("admin/topics")
public class TopicAdminController {

	@Autowired
	private TopicService topicService;

	@Autowired
	private SubjectService subjectService;

	@GetMapping
	public String showAllTopics(Model model) {
		model.addAttribute("topics", topicService.findAll());
		return "admin/topics";
	}

	@GetMapping("{id}")
	public String initUpdateForm(@PathVariable Long id, Model model) {
		Topic topic = topicService.findById(id);
		model.addAttribute("topic", topic);
		model.addAttribute("subjects", subjectService.findAll());
		return "admin/topic-edit";
	}

	@PostMapping("{id}")
	public String processUpdateForm(@Valid Topic topic, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(topic);
			model.addAttribute("subjects", subjectService.findAll());
			return "admin/topic-edit";
		}

		topicService.update(topic);

		redirectAttributes.addFlashAttribute("message",
				String.format("O tópico #%d - %s foi atualizado com sucesso.", topic.getId(), topic.getName()));

		return "redirect:/admin/topics";
	}

	@ResponseBody
	@PostMapping("drag/{draggedTopic}/{relatedTopic}")
	public ResponseEntity<Void> swapTopics(@PathVariable("draggedTopic") Long draggedTopic,
			@PathVariable("relatedTopic") Long relatedTopic) {
		topicService.dragTopic(draggedTopic, relatedTopic);
		return ResponseEntity.noContent().build();
	}

}
