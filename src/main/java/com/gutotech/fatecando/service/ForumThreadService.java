package com.gutotech.fatecando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gutotech.fatecando.model.ForumThread;
import com.gutotech.fatecando.model.Comment;

@Service
public class ForumThreadService {

	@Autowired
	private RestTemplate restTemplate;

	private final String URL = "http://localhost:8081/api/forum-threads";

	public List<Comment> findAllComments(ForumThread forumThread) {
		ResponseEntity<List<Comment>> responseEntity = restTemplate.exchange(URL + "/{id}/comments",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Comment>>() {
				}, forumThread.getId());

		return responseEntity.getBody();
	}

	public ForumThread findById(Long id) {
		return restTemplate.getForObject(URL + "/{id}", ForumThread.class, id);
	}

	public Comment saveComment(Comment comment, ForumThread forumThread) {
		return restTemplate.postForObject(URL + "/{id}/comments", comment, Comment.class, forumThread.getId());
	}

}
