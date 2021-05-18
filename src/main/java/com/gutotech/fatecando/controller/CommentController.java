package com.gutotech.fatecando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gutotech.fatecando.model.Comment;
import com.gutotech.fatecando.service.CommentService;

@Controller
@RequestMapping("comments")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@ResponseBody
	@PostMapping("{id}/upvote")
	public Comment addUpvote(@PathVariable Long id) {
		return commentService.addUpvote(id);
	}

	@ResponseBody
	@PostMapping("{id}/downvote")
	public Comment addDownvote(@PathVariable Long id) {
		return commentService.addDownvote(id);
	}

	@ResponseBody
	@PostMapping("{id}/accept")
	public Comment accept(@PathVariable Long id) {
		return commentService.accept(id);
	}

}
