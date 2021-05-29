package com.gutotech.fatecando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.gutotech.fatecando.model.Course;

@Service
public class CourseService {

	@Autowired
	private CustomRestTemplate restTemplate;

	private final String URL = "http://localhost:8081/api/courses";

	public List<Course> findAll() {
		return restTemplate.getForObjects(URL, new ParameterizedTypeReference<List<Course>>() {});
	}

	public Course findById(Long id) {
		return restTemplate.getForObject(URL + "/{id}", Course.class, id);
	}

	public Course save(Course course) {
		return restTemplate.postForObject(URL, course, Course.class);
	}

	public void update(Course course) {
		restTemplate.put(URL + "/{id}", course, course.getId());
	}

}
