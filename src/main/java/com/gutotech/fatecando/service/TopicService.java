package com.gutotech.fatecando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gutotech.fatecando.model.Topic;

@Service
public class TopicService {

	@Autowired
	private RestTemplate restTemplate;

	private final String URL = "http://localhost:8081/api/topics";

	public List<Topic> findAll() {
		ResponseEntity<List<Topic>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Topic>>() {
				});

		return responseEntity.getBody();
	}

	public List<Topic> findAllFavorites() {
		ResponseEntity<List<Topic>> responseEntity = restTemplate.exchange(URL + "/favorites", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Topic>>() {
				});

		return responseEntity.getBody();
	}

	public Topic findById(Long id) {
		return restTemplate.getForObject(URL + "/" + id, Topic.class);
	}

	public Topic findByIdWithPreviousAndNext(Long id) {
		return restTemplate.getForObject(URL + "/{id}?previous-next=true", Topic.class, id);
	}

	public Topic save(Topic topic) {
		return restTemplate.postForObject(URL, topic, Topic.class);
	}

	public void update(Topic topic) {
		restTemplate.put(URL + "/{id}", topic, topic.getId());
	}

	public void toggleFinished(Topic topic) {
		restTemplate.put(URL + "/{id}/finished", null, topic.getId());
	}

	public void toggleLike(Topic topic) {
		restTemplate.put(URL + "/{id}/like", null, topic.getId());
	}

	public void toggleFavorite(Topic topic) {
		restTemplate.put(URL + "/{id}/favorite", null, topic.getId());
	}

	public void dragTopic(Long draggedTopicId, Long relatedTopicId) {
		restTemplate.put(URL + "/drag/{draggedTopicId}/{relatedTopicId}", null, draggedTopicId, relatedTopicId);
	}
}
