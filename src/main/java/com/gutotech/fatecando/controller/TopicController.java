package com.gutotech.fatecando.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.fatecando.model.Alternative;
import com.gutotech.fatecando.model.Feedback;
import com.gutotech.fatecando.model.Question;
import com.gutotech.fatecando.model.Review;
import com.gutotech.fatecando.model.Topic;
import com.gutotech.fatecando.service.QuestionService;
import com.gutotech.fatecando.service.TopicService;

@Controller
@RequestMapping("topic/{topicId}")
public class TopicController {

	@Autowired
	private TopicService topicService;

	@Autowired
	private QuestionService questionService;

	@ModelAttribute("topic")
	public Topic getTopic(@PathVariable("topicId") Long topicId) {
		return topicService.findByIdWithPreviousAndNext(topicId);
	}

	@InitBinder("topic")
	public void initSubjectBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping
	public String showTopic(Topic topic, Model model) {
		Question question = new Question();
		question.getAlternatives().addAll(Arrays.asList( //
				new Alternative("Alternativa 1", new Feedback(true)), //
				new Alternative("Alternativa 2", new Feedback())));

		model.addAttribute("question", question);
		model.addAttribute("questions", topicService.getQuiz(topic));
		return "subjects/topic-details";
	}

	@PostMapping("question")
	public String addQuestion(Topic topic, Question question, RedirectAttributes redirectAttributes, Model model) {
		if (question.getAlternatives().size() > 6) {
			model.addAttribute("question", question);
			return "subjects/topic-details";
		}
		
		question.setTopic(topic);

		questionService.save(question);

		redirectAttributes.addFlashAttribute("successMessage", "Obrigado pela contribuição!");

		return "redirect:/topic/{topicId}";
	}

	@PostMapping("finished")
	public ResponseEntity<Void> toggleFinished(Topic topic) {
		topicService.toggleFinished(topic);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("fav")
	public ResponseEntity<Void> toggleFavorite(Topic topic) {
		topicService.toggleFavorite(topic);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("annotation")
	public ResponseEntity<Void> saveAnnotation(Topic topic, @RequestBody String annotation) {
		topicService.saveAnnotation(topic, annotation);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("review")
	public ResponseEntity<Void> saveReview(Topic topic, @RequestBody Review review) {
		topicService.saveReview(topic, review);
		return ResponseEntity.noContent().build();
	}

}
