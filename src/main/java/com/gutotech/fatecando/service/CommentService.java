package com.gutotech.fatecando.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gutotech.fatecando.model.Comment;

@Service
public class CommentService {

	@Autowired
	private RestTemplate restTemplate;

	private final String URL = "http://localhost:8081/api/comments";

	public Comment findById(Long id) {
		return restTemplate.getForObject(URL + "/{id}", Comment.class, id);
	}

	public Comment addUpvote(Long id) {
		return restTemplate.postForObject(URL + "/{id}/upvote", null, Comment.class, id);
	}

	public Comment addDownvote(Long id) {
		return restTemplate.postForObject(URL + "/{id}/downvote", null, Comment.class, id);
	}

}
