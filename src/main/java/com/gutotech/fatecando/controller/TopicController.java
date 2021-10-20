package com.gutotech.fatecando.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import com.gutotech.fatecando.model.QuestionType;
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
	public void initTopicBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
		CustomCollectionEditor topicsEditor = new CustomCollectionEditor(List.class) {
			@Override
			protected Object convertElement(Object element) {
				if (element instanceof String) {
					String name = (String) element;
					QuestionType type = new QuestionType(name);
					return type;
				}
				throw new RuntimeException("Invalid element");
			}
		};

		binder.registerCustomEditor(List.class, "questionTypes", topicsEditor);
	}

	@GetMapping
	public String showTopic(Topic topic, Model model) {
		Question question = new Question();
		question.getAlternatives().addAll(Arrays.asList( //
				new Alternative(null, new Feedback(true)), //
				new Alternative(null, new Feedback())));

		model.addAttribute("questionTypes", QuestionType.getAllTypes());
		model.addAttribute("question", question);
		model.addAttribute("questions", topicService.getQuiz(topic));
		return "subjects/topic-details";
	}

	@PostMapping
	public String addQuestion(Topic topic, @Valid Question question, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("question", question);
			model.addAttribute("questionTypes", QuestionType.getAllTypes());
			model.addAttribute("questions", topicService.getQuiz(topic));
			model.addAttribute("error", true);
			return "subjects/topic-details";
		}

		question.setTopic(topic);

		questionService.save(question);

		redirectAttributes.addFlashAttribute("successMessage",
				"Obrigado pela contribuição! A questão enviada será analisada pela nossa equipe e se tudo estiver certo será publicada o mais rápido possível.");

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
