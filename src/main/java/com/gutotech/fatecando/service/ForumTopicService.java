package com.gutotech.fatecando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gutotech.fatecando.model.ForumTopic;
import com.gutotech.fatecando.model.ForumTopicComment;

@Service
public class ForumTopicService {

	@Autowired
	private RestTemplate restTemplate;

	private final String URL = "http://localhost:8081/api/forum-topics";

	public List<ForumTopicComment> findAllComments(ForumTopic forumTopic) {
		ResponseEntity<List<ForumTopicComment>> responseEntity = restTemplate.exchange(URL + "/{id}/comments",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<ForumTopicComment>>() {
				}, forumTopic.getId());

		return responseEntity.getBody();
	}

	public ForumTopic findById(Long id) {
		return restTemplate.getForObject(URL + "/{id}", ForumTopic.class, id);
	}

	public ForumTopicComment saveComment(ForumTopicComment comment, ForumTopic forumTopic) {
		return restTemplate.postForObject(URL + "/{id}/comments", comment, ForumTopicComment.class, forumTopic.getId());
	}

}
