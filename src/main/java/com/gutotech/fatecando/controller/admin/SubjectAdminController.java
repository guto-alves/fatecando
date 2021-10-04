package com.gutotech.fatecando.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.fatecando.model.Subject;
import com.gutotech.fatecando.security.Roles;
import com.gutotech.fatecando.service.SubjectService;
import com.gutotech.fatecando.service.UserService;

@Controller
@RequestMapping("admin/subjects")
public class SubjectAdminController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private SubjectService subjectService;

	@GetMapping
	public String initSubjectsAdminPage(Model model) {
		model.addAttribute("subject", new Subject());
		
		if (userService.hasRoles(Roles.TEACHER)) {
			model.addAttribute("subjects", userService.findMySubjects());
		} else {
			model.addAttribute("subjects", subjectService.findAll());			
		}
		
		return "admin/subjects";
	}

	@PostMapping
	public String processSubjectCreationForm(@Valid Subject subject, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(subject);
			model.addAttribute("subjects", subjectService.findAll());
			return "admin/subjects";
		}

		subjectService.save(subject);

		redirectAttributes.addFlashAttribute("message", "Disciplina " + subject.getName() + " criada com sucesso");

		return "redirect:/admin/subjects";
	}

	@PostMapping("update")
	public String processSubjectCreationForm(@RequestBody Subject subject, BindingResult bindingResult) {
		subjectService.update(subject);
		return "redirect:/admin/subjects";
	}
}
