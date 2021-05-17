package com.gutotech.fatecando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gutotech.fatecando.model.ForumThread;
import com.gutotech.fatecando.service.ForumThreadService;

@Controller
@RequestMapping("forum")
public class ForumController {

	@Autowired
	private ForumThreadService forumThreadService;

	@ResponseBody
	@PostMapping("{id}/upvote")
	public ForumThread addUpvote(@PathVariable Long id) {
		return forumThreadService.addUpvote(id);
	}

	@ResponseBody
	@PostMapping("{id}/downvote")
	public ForumThread addDownvote(@PathVariable Long id) {
		return forumThreadService.addDownvote(id);
	}

}
