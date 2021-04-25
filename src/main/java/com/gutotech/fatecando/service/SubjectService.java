package com.gutotech.fatecando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gutotech.fatecando.model.Course;
import com.gutotech.fatecando.model.ForumThread;
import com.gutotech.fatecando.model.Subject;
import com.gutotech.fatecando.model.Topic;

@Service
public class SubjectService {

	@Autowired
	private RestTemplate restTemplate;

	private final String URL = "http://localhost:8081/api/subjects";

	public Subject findById(Long id) {
		return restTemplate.getForObject(URL + "/" + id, Subject.class);
	}

	public List<Subject> findAll() {
		ResponseEntity<List<Subject>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Subject>>() {
				});

		return responseEntity.getBody();
	}

	public List<Subject> findAllWithTopics() {
		ResponseEntity<List<Subject>> responseEntity = restTemplate.exchange(URL + "?with-topics=true", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Subject>>() {
				});

		return responseEntity.getBody();
	}

	public List<Subject> findAllByCourse(Course course) {
		ResponseEntity<List<Subject>> responseEntity = restTemplate.exchange(URL + "?course={courseId}", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Subject>>() {
				}, course.getId());

		return responseEntity.getBody();
	}

	public List<Topic> findAllTopicsBySubject(Subject subject) {
		ResponseEntity<List<Topic>> responseEntity = restTemplate.exchange(URL + "/{id}/topics", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Topic>>() {
				}, subject.getId());

		return responseEntity.getBody();
	}

	public List<ForumThread> findAllForumTopicsBySubject(Subject subject) {
		ResponseEntity<List<ForumThread>> responseEntity = restTemplate.exchange(URL + "/{id}/forum-topics",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<ForumThread>>() {
				}, subject.getId());

		return responseEntity.getBody();
	}

	public Subject save(Subject subject) {
		return restTemplate.postForObject(URL, subject, Subject.class);
	}

	public void update(Subject subject) {
		restTemplate.put(URL + "/{id}", subject, subject.getId());
	}

	public void toggleLike(Subject subject) {
		restTemplate.put(URL + "/{id}/like", null, subject.getId());
	}

	public ForumThread saveForumThread(Subject subject, ForumThread forumThread) {
		return restTemplate.postForObject(URL + "/{id}/forum-topics", forumThread, ForumThread.class, subject.getId());
	}

}
