package com.gutotech.fatecando.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.fatecando.model.Subject;
import com.gutotech.fatecando.model.User;
import com.gutotech.fatecando.service.SubjectService;
import com.gutotech.fatecando.service.UserService;

@Controller
@RequestMapping("admin/users")
public class UserAdminController {

	@Autowired
	private UserService userService;
	
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

	@ModelAttribute("users")
	public List<User> getUsers() {
		return userService.findAll();
	}

	@GetMapping
	public String showUsersPage() {
		return "admin/users";
	}

	@GetMapping("{id}")
	public String showUserPage(@PathVariable Long id, Model model) {
		User user = userService.findById(id);
		model.addAttribute("user", user);
		model.addAttribute("mySubjects", userService.findMySubjects(user));
		model.addAttribute("allSubjects", subjectService.findAll());
		return "admin/user-profile";
	}

	@PostMapping("{id}")
	public String proccessUserPage(@PathVariable Long id, User user, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(user);
			model.addAttribute("mySubjects", userService.findMySubjects());
			model.addAttribute("allSubjects", subjectService.findAll());
			return "admin/user-profile";
		}

		userService.update(user);
		
		redirectAttributes.addFlashAttribute("message", "Perfil atualizado com sucesso");
		
		return "redirect:/admin/users/" + id;
	}
}
