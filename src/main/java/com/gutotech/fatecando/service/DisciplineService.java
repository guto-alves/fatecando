package com.gutotech.fatecando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gutotech.fatecando.model.Course;
import com.gutotech.fatecando.model.Discipline;
import com.gutotech.fatecando.model.ForumTopic;
import com.gutotech.fatecando.model.Topic;

@Service
public class DisciplineService {

	@Autowired
	private RestTemplate restTemplate;

	private final String URL = "http://localhost:8081/api/disciplines";

	public Discipline findById(Long id) {
		return restTemplate.getForObject(URL + "/" + id, Discipline.class);
	}

	public List<Discipline> findAll() {
		ResponseEntity<List<Discipline>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Discipline>>() {
				});

		return responseEntity.getBody();
	}

	public List<Discipline> findAllWithTopics() {
		ResponseEntity<List<Discipline>> responseEntity = restTemplate.exchange(URL + "?with-topics=true",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Discipline>>() {
				});

		return responseEntity.getBody();
	}

	public List<Discipline> findAllAccessed() {
		ResponseEntity<List<Discipline>> responseEntity = restTemplate.exchange(URL + "/accessed", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Discipline>>() {
				});

		return responseEntity.getBody();
	}

	public List<Discipline> findAllByCourse(Course course) {
		ResponseEntity<List<Discipline>> responseEntity = restTemplate.exchange(URL + "?course={courseId}",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Discipline>>() {
				}, course.getId());

		return responseEntity.getBody();
	}

	public List<Topic> findAllTopicsByDiscipline(Discipline discipline) {
		ResponseEntity<List<Topic>> responseEntity = restTemplate.exchange(URL + "/{id}/topics", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Topic>>() {
				}, discipline.getId());

		return responseEntity.getBody();
	}

	public List<ForumTopic> findAllForumTopicsByDiscipline(Discipline discipline) {
		ResponseEntity<List<ForumTopic>> responseEntity = restTemplate.exchange(URL + "/{id}/forum-topics",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<ForumTopic>>() {
				}, discipline.getId());

		return responseEntity.getBody();
	}

	public Discipline save(Discipline discipline) {
		return restTemplate.postForObject(URL, discipline, Discipline.class);
	}

	public void update(Discipline discipline) {
		restTemplate.put(URL + "/{id}", discipline, discipline.getId());
	}

	public void toggleLike(Discipline discipline) {
		restTemplate.put(URL + "/{id}/like", null, discipline.getId());
	}

	public ForumTopic saveForumTopic(Discipline discipline, ForumTopic forumTopic) {
		return restTemplate.postForObject(URL + "/{id}/forum-topics", forumTopic, ForumTopic.class, discipline.getId());
	}

}
