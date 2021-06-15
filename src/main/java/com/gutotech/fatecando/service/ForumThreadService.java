package com.gutotech.fatecando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.gutotech.fatecando.model.Comment;
import com.gutotech.fatecando.model.ForumThread;
import com.gutotech.fatecando.model.Subject;

@Service
public class ForumThreadService {

	@Autowired
	private CustomRestTemplate restTemplate;

	private final String URL;

	public ForumThreadService(@Value("${fatecando.api.base-url}") String url) {
		URL = url + "/forum-threads";
	}

	public List<ForumThread> findForumThreadBy(Subject subject, String sortBy, List<String> filters,
			List<Long> topics) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL).queryParam("subject", subject.getId())
				.queryParam("sort", sortBy).queryParam("filter", filters).queryParam("topic", topics);

		return restTemplate.getForObjects(builder.toUriString(), new ParameterizedTypeReference<List<ForumThread>>() {
		}, subject.getId(), sortBy, filters, topics);
	}

	public ForumThread findById(Long id) {
		return restTemplate.getForObject(URL + "/{id}", ForumThread.class, id);
	}

	public ForumThread addUpvote(Long id) {
		return restTemplate.postForObject(URL + "/{id}/upvote", null, ForumThread.class, id);
	}

	public ForumThread addDownvote(Long id) {
		return restTemplate.postForObject(URL + "/{id}/downvote", null, ForumThread.class, id);
	}

	public List<Comment> findAllComments(ForumThread forumThread) {
		return restTemplate.getForObjects(URL + "/{id}/comments", new ParameterizedTypeReference<List<Comment>>() {
		}, forumThread.getId());
	}

	public Comment saveComment(Comment comment, ForumThread forumThread) {
		return restTemplate.postForObject(URL + "/{id}/comments", comment, Comment.class, forumThread.getId());
	}

}
