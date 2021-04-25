package com.gutotech.fatecando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gutotech.fatecando.model.Course;
import com.gutotech.fatecando.service.CourseService;
import com.gutotech.fatecando.service.SubjectService;

@Controller
@RequestMapping("course")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private SubjectService subjectService;

	@GetMapping("{id}")
	public String showCourseInfo(@PathVariable("id") Long id, Model model) {
		Course course = courseService.findById(id);

		model.addAttribute("course", course);
		model.addAttribute("subjects", subjectService.findAllByCourse(course));

		return "subjects/course-details";
	}

}
