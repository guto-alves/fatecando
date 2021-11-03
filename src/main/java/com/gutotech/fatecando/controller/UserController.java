package com.gutotech.fatecando.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.fatecando.model.PasswordForm;
import com.gutotech.fatecando.model.Subject;
import com.gutotech.fatecando.model.Topic;
import com.gutotech.fatecando.model.UploadStatus;
import com.gutotech.fatecando.model.User;
import com.gutotech.fatecando.service.SubjectService;
import com.gutotech.fatecando.service.TopicService;
import com.gutotech.fatecando.service.UserService;

@Controller
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private TopicService topicService;

	@Autowired
	private SubjectService subjectService;

	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
		CustomCollectionEditor topicsEditor = new CustomCollectionEditor(List.class) {
			@Override
			protected Object convertElement(Object element) {
				if (element instanceof String) {
					Long id = Long.parseLong((String) element);
					Subject subject = new Subject();
					subject.setId(id);
					return subject;
				}
				throw new RuntimeException("Invalid element");
			}
		};

		binder.registerCustomEditor(List.class, "subjects", topicsEditor);
	}

	@ModelAttribute("user")
	public User getUser() {
		return userService.findCurrentUser();
	}

	@GetMapping("{id}")
	public String showUserProfilePage(@PathVariable Long id, Model model) {
		model.addAttribute("user", userService.findById(id));
		return "users/profile";
	}

	@GetMapping("edit-profile")
	public String initUpdateForm(Model model) {
		model.addAttribute("mySubjects", userService.findMySubjects());
		model.addAttribute("allSubjects", subjectService.findAll());
		return "users/edit-profile";
	}

	@PostMapping("edit-profile")
	public String processUpdateForm(@Valid User user, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(user);
			model.addAttribute("mySubjects", userService.findMySubjects());
			model.addAttribute("allSubjects", subjectService.findAll());
			return "users/edit-profile";
		}

		userService.update(user);

		redirectAttributes.addFlashAttribute("message", "Perfil atualizado com sucesso.");

		return "redirect:/users/edit-profile";
	}

	@GetMapping("account")
	public String showPasswordUpdatePage(Model model) {
		model.addAttribute("form", new PasswordForm());
		return "users/account";
	}

	@PostMapping("account")
	public String proccessPasswordUpdate(@Valid PasswordForm passwordForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("form", passwordForm);
			return "users/account";
		}
		
		userService.updatePassword(passwordForm);

		redirectAttributes.addFlashAttribute("successMessage", "Senha atualizada com sucesso.");
		return "redirect:/users/account";
	}

	@GetMapping("favorites")
	public String showFavoriteTopicsPage(Model model) {
		model.addAttribute("subjectTopicsMap", topicService.groupBySubject(userService.findFavoriteTopics()));
		return "users/my-favorite-topics";
	}

	@GetMapping("annotations")
	public String showAnnotationsPage(Model model) {
		List<Topic> topics = userService.findAnnotatedTopics();
		model.addAttribute("topics", topics);
		model.addAttribute("subjectTopicsMap", topicService.groupBySubject(topics));
		return "users/my-annotations";
	}

	@GetMapping("topics")
	public String showUserTopicsPage(Model model) {
		model.addAttribute("topics", userService.findMyTopics());
		return "users/my-topics";
	}

	@GetMapping("topic/{id}")
	public String initUpdateForm(@PathVariable Long id, Model model) {
		Topic topic = topicService.findById(id);
		String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

		if (!topic.getCreatedBy().getEmail().equals(currentUserEmail)) {
			return "redirect:/users/topics";
		}

		model.addAttribute("topic", topic);
		model.addAttribute("subjects", subjectService.findAll());
		return "users/topic-edit";
	}

	@PostMapping("topic/{id}")
	public String processUpdateForm(@Valid Topic topic, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(topic);
			model.addAttribute("subjects", subjectService.findAll());
			return "users/topic-edit";
		}

		topic.setStatus(UploadStatus.EDITED);
		topicService.update(topic);

		redirectAttributes.addFlashAttribute("message",
				String.format("O tópico #%d - <b>%s</b> foi atualizado com sucesso.", topic.getId(), topic.getName()));

		return "redirect:/users/topics";
	}

	@ResponseBody
	@GetMapping("search")
	public List<User> search(@RequestParam String filter) {
		return userService.searchByNameOrEmail(filter);
	}
}
