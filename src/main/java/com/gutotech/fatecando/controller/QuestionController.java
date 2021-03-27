package com.gutotech.fatecando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gutotech.fatecando.model.Feedback;
import com.gutotech.fatecando.service.QuestionService;

@Controller
@RequestMapping("questions")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@ResponseBody
	@PostMapping("{questionId}/answer/{alternativeId}")
	public Feedback answerQuestion(@PathVariable("questionId") Long questionId,
			@PathVariable("alternativeId") Long alternativeId) {
		return questionService.answer(questionId, alternativeId);
	}

}
