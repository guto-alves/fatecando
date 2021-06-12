package com.gutotech.fatecando.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.fatecando.model.Topic;
import com.gutotech.fatecando.model.User;
import com.gutotech.fatecando.service.TopicService;
import com.gutotech.fatecando.service.UserService;

@Controller
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private TopicService topicService;

	@GetMapping("{id}")
	public String showUserProfilePage(@PathVariable Long id, Model model) {
		model.addAttribute("user", userService.findById(id));
		return "users/profile";
	}

	@GetMapping("edit-profile")
	public String initUpdateForm() {
		return "users/edit-profile";
	}

	@PostMapping("edit-profile")
	public String processUpdateForm(@Valid User user, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(user);
			return "users/edit-profile";
		}

		userService.update(user);

		redirectAttributes.addFlashAttribute("message", "Perfil atualizado com sucesso");

		return "redirect:/users/edit-profile";
	}

	@GetMapping("favorites")
	public String showFavoriteTopicsPage(Model model) {
		model.addAttribute("topics", userService.findFavoriteTopics());
		return "users/favorite-topics";
	}

	@GetMapping("annotations")
	public String showAnnotationsPage(Model model) {
		model.addAttribute("topics", userService.findAnnotatedTopics());
		return "users/annotations";
	}

	@GetMapping("topics")
	public String showUserTopicsPage(Model model) {
		model.addAttribute("topics", userService.findAllTopics());
		return "users/topics";
	}

	@GetMapping("topic/{id}")
	public String initUpdateForm(@PathVariable Long id, Model model) {
		Topic topic = topicService.findById(id);
		String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

		if (!topic.getCreatedBy().getEmail().equals(currentUserEmail)) {
			return "redirect:/users/topics";
		}

		model.addAttribute("topic", topic);
		return "users/topic-edit";
	}

	@PostMapping("topic/{id}")
	public String processUpdateForm(@Valid Topic topic, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(topic);
			return "users/topic-edit";
		}

		topicService.update(topic);

		redirectAttributes.addFlashAttribute("message",
				String.format("O tópido <b>%s</b> foi atualizado com sucesso.", topic.getName()));

		return "redirect:/users/topics";
	}

	@ResponseBody
	@GetMapping("search")
	public List<User> search(@RequestParam String filter) {
		return userService.searchByNameOrEmail(filter);
	}
}
