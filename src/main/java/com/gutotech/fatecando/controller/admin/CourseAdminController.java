package com.gutotech.fatecando.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.fatecando.model.Course;
import com.gutotech.fatecando.model.Institution;
import com.gutotech.fatecando.service.CourseService;
import com.gutotech.fatecando.service.InstitutionService;

@Controller
@RequestMapping("admin/courses")
public class CourseAdminController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private InstitutionService institutionService;

	@ModelAttribute("institutions")
	public List<Institution> getInstitutions() {
		return institutionService.findAll();
	}

	@ModelAttribute("courses")
	public List<Course> getCourses() {
		return courseService.findAll();
	}

	@GetMapping
	public String showCoursesPage(Model model) {
		model.addAttribute("course", new Course());
		return "admin/courses";
	}

	@PostMapping
	public String processCourseCreationForm(@Valid Course course, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("errors", true);
			model.addAttribute(course);
			return "admin/courses";
		}

		courseService.save(course);

		redirectAttributes.addFlashAttribute("message", "Curso " + course.getName() + " foi cadastrado com sucesso.");

		return "redirect:/admin/courses";
	}

	@PostMapping("update")
	public String processCourseCreationForm(@Valid @RequestBody Course course) {
		courseService.update(course);
		return "redirect:/admin/courses";
	}
}
