package com.gutotech.fatecando.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.fatecando.model.Discipline;
import com.gutotech.fatecando.model.ForumTopic;
import com.gutotech.fatecando.model.ForumTopicComment;
import com.gutotech.fatecando.model.Test;
import com.gutotech.fatecando.model.Topic;
import com.gutotech.fatecando.model.UploadStatus;
import com.gutotech.fatecando.service.DisciplineService;
import com.gutotech.fatecando.service.ForumTopicService;
import com.gutotech.fatecando.service.TestService;
import com.gutotech.fatecando.service.TopicService;

@Controller
@RequestMapping("disciplines/{disciplineId}")
public class DisciplineController {

	@Autowired
	private DisciplineService disciplineService;

	@Autowired
	private ForumTopicService forumTopicService;

	@Autowired
	private TopicService topicService;

	@ModelAttribute("discipline")
	public Discipline findDiscipline(@PathVariable("disciplineId") Long disciplineId) {
		return disciplineService.findById(disciplineId);
	}

	@InitBinder("discipline")
	public void initDisciplineBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id", "name");
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
		CustomCollectionEditor topicsEditor = new CustomCollectionEditor(List.class) {
			@Override
			protected Object convertElement(Object element) {
				if (element instanceof String) {
					Long id = Long.parseLong((String) element);
					Topic topic = new Topic();
					topic.setId(id);
					return topic;
				}
				throw new RuntimeException("Invalid element");
			}
		};

		binder.registerCustomEditor(List.class, "topics", topicsEditor);
		binder.registerCustomEditor(List.class, "tags", topicsEditor);
	}

	@GetMapping
	public String showDiscipline(Discipline discipline,
			@RequestParam(value = "page", required = false, defaultValue = "topics") String page, Model model) {
		model.addAttribute("page", page);

		switch (page) {
		case "tests":
			model.addAttribute("test", new Test(discipline.getName()));
			break;
		case "forum":
			model.addAttribute("forumTopic", new ForumTopic());
			model.addAttribute("forumTopics", disciplineService.findAllForumTopicsByDiscipline(discipline));
			break;
		default:
			model.addAttribute("page", "topics");
			break;
		}

		model.addAttribute("topic", new Topic());
		model.addAttribute("topics", disciplineService.findAllTopicsByDiscipline(discipline));

		return "disciplines/discipline-details";
	}

	@PostMapping("like")
	public ResponseEntity<Void> toggleLike(Discipline discipline) {
		disciplineService.toggleLike(discipline);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("forum-topics")
	public String processForumTopicCreationForm(Discipline discipline, @Valid ForumTopic forumTopic,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("message",
					"Não foi possível criar o tópico para o fórum:\nTítulo ou descrição inválido!");
			return "redirect:/disciplines/{disciplineId}?page=forum";
		}

		disciplineService.saveForumTopic(discipline, forumTopic);

		redirectAttributes.addFlashAttribute("message", "Tópico para o Fórum da disciplina foi criado com sucesso.");

		return "redirect:/disciplines/{disciplineId}?page=forum";
	}

	@GetMapping("forum-topics/{topicId}")
	public String showForumTopic(Discipline discipline, @PathVariable("topicId") Long topicId, Model model) {
		ForumTopic forumTopic = forumTopicService.findById(topicId);

		model.addAttribute("forumTopic", forumTopic);
		model.addAttribute("comments", forumTopicService.findAllComments(forumTopic));
		model.addAttribute("comment", new ForumTopicComment());

		return "disciplines/forum-topic";
	}

	@PostMapping("forum-topics/{topicId}")
	public String processCommentCreationForm(@PathVariable("topicId") Long topicId, @Valid ForumTopicComment comment,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Discipline discipline, Model model) {
		ForumTopic forumTopic = forumTopicService.findById(topicId);

		if (bindingResult.hasErrors()) {
			model.addAttribute("comment", comment);
			model.addAttribute("comments", forumTopicService.findAllComments(forumTopic));
			model.addAttribute("forumTopic", forumTopic);
			return "disciplines/forum-topic";
		}

		forumTopicService.saveComment(comment, forumTopic);

		redirectAttributes.addFlashAttribute("message", "Comentário adicionado com sucesso.");

		return "redirect:/disciplines/{disciplineId}/forum-topics/{topicId}";
	}

	@PostMapping
	public String processTopicCreationForm(Discipline discipline, @Valid Topic topic, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(topic);
			model.addAttribute("topics", disciplineService.findAllTopicsByDiscipline(discipline));
			model.addAttribute("page", "topics");
			model.addAttribute("error", true);
			return "disciplines/discipline-details";
		}

		topic.setDiscipline(discipline);
		topic.setStatus(UploadStatus.WAITING_FOR_RESPONSE);
		topicService.save(topic);

		redirectAttributes.addFlashAttribute("message",
				"Obrigado pela contribuição! O conteúdo enviado será analisado pela nossa equipe e se tudo estiver certo ele será publicado o mais rápido possível.");

		return "redirect:/disciplines/{disciplineId}";
	}

	@Autowired
	private TestService testService;

	@PostMapping("test")
	public String processTestCreationForm(Discipline discipline, @Valid Test test, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(test);
			model.addAttribute("discipline", discipline);
			model.addAttribute("topics", disciplineService.findAllTopicsByDiscipline(discipline));
			model.addAttribute("page", "tests");
			model.addAttribute("topic", new Topic());
			return "disciplines/discipline-details";
		}

		test.setDiscipline(discipline);

		testService.save(test);

		return "redirect:/test";
	}

}
