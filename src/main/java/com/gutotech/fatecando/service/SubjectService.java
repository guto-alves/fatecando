package com.gutotech.fatecando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.gutotech.fatecando.model.ForumThread;
import com.gutotech.fatecando.model.Subject;
import com.gutotech.fatecando.model.Topic;

@Service
public class SubjectService {

	@Autowired
	private CustomRestTemplate restTemplate;

	private final String URL;

	public SubjectService(@Value("${fatecando.api.base-url}") String url) {
		URL = url + "/subjects";
	}

	public Subject findById(Long id) {
		return restTemplate.getForObject(URL + "/" + id, Subject.class);
	}

	public List<Subject> findAll() {
		return restTemplate.getForObjects(URL, new ParameterizedTypeReference<List<Subject>>() {
		});
	}

	public List<Subject> findAllWithTopics() {
		return restTemplate.getForObjects(URL + "?with-topics=true", new ParameterizedTypeReference<List<Subject>>() {
		});
	}

	public List<Topic> findAllTopicsBySubject(Subject subject) {
		return restTemplate.getForObjects(URL + "/{id}/topics", new ParameterizedTypeReference<List<Topic>>() {
		}, subject.getId());
	}

	public List<ForumThread> findAllForumTopicsBySubject(Subject subject) {
		return restTemplate.getForObjects(URL + "/{id}/forum-topics",
				new ParameterizedTypeReference<List<ForumThread>>() {
				}, subject.getId());
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
