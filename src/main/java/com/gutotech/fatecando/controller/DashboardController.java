package com.gutotech.fatecando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gutotech.fatecando.model.Course;
import com.gutotech.fatecando.service.CourseService;
import com.gutotech.fatecando.service.SubjectService;
import com.gutotech.fatecando.service.UserService;

@Controller
@RequestMapping("dashboard")
public class DashboardController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private UserService userService;

	@GetMapping
	public String home(Model model) {
		Course course = courseService.findById(1L); // get ADS course

		model.addAttribute("course", course);
		model.addAttribute("subjects", subjectService.findAllByCourse(course));
		model.addAttribute("recentSubjects", userService.findSubjectsAccessed());

		return "subjects/course-details";
	}

}
