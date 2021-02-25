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

import com.gutotech.fatecando.model.Course;
import com.gutotech.fatecando.service.CourseService;

@Controller
@RequestMapping("admin/courses")
public class CourseAdminController {

	@Autowired
	private CourseService courseService;

	@GetMapping
	public String showCoursesPage(Model model) {
		model.addAttribute("courses", courseService.findAll());
		model.addAttribute("course", new Course());
		return "admin/courses";
	}

	@PostMapping
	public String processCourseCreationForm(@Valid Course course, Model model, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(course);
			return "admin/courses";
		}

		courseService.save(course);

		redirectAttributes.addFlashAttribute("message", "Curso " + course.getName() + " foi criado com sucesso");

		return "redirect:/admin/courses";
	}

	@PostMapping("update")
	public String processCourseCreationForm(@RequestBody Course course) {
		courseService.update(course);
		return "redirect:/admin/courses";
	}
}
