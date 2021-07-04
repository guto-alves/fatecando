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
import com.gutotech.fatecando.service.CourseService;
import com.gutotech.fatecando.service.SubjectService;

@Controller
@RequestMapping("admin/subjects")
public class SubjectAdminController {

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private CourseService courseService;

	@GetMapping
	public String initSubjectsAdminPage(Model model) {
		model.addAttribute("subjects", subjectService.findAll());
		model.addAttribute("subject", new Subject());
		model.addAttribute("courses", courseService.findAll());
		return "admin/subjects";
	}

	@PostMapping
	public String processSubjectCreationForm(@Valid Subject subject, Model model, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(subject);
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
