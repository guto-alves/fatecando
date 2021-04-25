package com.gutotech.fatecando.controller;

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
@RequestMapping("admin/disciplines")
public class SubjectAdminController {

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private CourseService courseService;

	@GetMapping
	public String initDisciplinesAdminPage(Model model) {
		model.addAttribute("disciplines", subjectService.findAll());
		model.addAttribute("discipline", new Subject());
		model.addAttribute("courses", courseService.findAll());
		return "admin/disciplines";
	}

	@PostMapping
	public String processDisciplineCreationForm(@Valid Subject subject, Model model, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(subject);
			return "admin/disciplines";
		}

		subjectService.save(subject);

		redirectAttributes.addFlashAttribute("message", "Disciplina " + subject.getName() + " criada com sucesso");

		return "redirect:/admin/disciplines";
	}

	@PostMapping("update")
	public String processDisciplineCreationForm(@RequestBody Subject subject, BindingResult bindingResult) {
		subjectService.update(subject);
		return "redirect:/admin/disciplines";
	}
}
