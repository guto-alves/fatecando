package com.gutotech.fatecando.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gutotech.fatecando.model.Comment;

@Service
public class CommentService {

	@Autowired
	private CustomRestTemplate restTemplate;

	private final String URL;

	public CommentService(@Value("${fatecando.api.base-url}") String url) {
		URL = url + "/comments";
	}

	public Comment findById(Long id) {
		return restTemplate.getForObject(URL + "/{id}", Comment.class, id);
	}

	public Comment addUpvote(Long id) {
		return restTemplate.postForObject(URL + "/{id}/upvote", null, Comment.class, id);
	}

	public Comment addDownvote(Long id) {
		return restTemplate.postForObject(URL + "/{id}/downvote", null, Comment.class, id);
	}

	public Comment accept(Long id) {
		return restTemplate.postForObject(URL + "/{id}/accept", null, Comment.class, id);
	}

}
