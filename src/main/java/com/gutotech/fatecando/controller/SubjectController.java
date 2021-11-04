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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.fatecando.model.Comment;
import com.gutotech.fatecando.model.ForumThread;
import com.gutotech.fatecando.model.Subject;
import com.gutotech.fatecando.model.Test;
import com.gutotech.fatecando.model.Topic;
import com.gutotech.fatecando.model.UploadStatus;
import com.gutotech.fatecando.security.Roles;
import com.gutotech.fatecando.service.ForumThreadService;
import com.gutotech.fatecando.service.SubjectService;
import com.gutotech.fatecando.service.TestService;
import com.gutotech.fatecando.service.TopicService;
import com.gutotech.fatecando.service.UserService;

@Controller
@RequestMapping("subjects/{subjectId}")
public class SubjectController {

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private ForumThreadService forumThreadService;

	@Autowired
	private TopicService topicService;

	@Autowired
	private UserService userService;

	@ModelAttribute("subject")
	public Subject findSubject(@PathVariable("subjectId") Long subjectId) {
		return subjectService.findById(subjectId);
	}

	@InitBinder("subject")
	public void initSubjectBinder(WebDataBinder dataBinder) {
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
	public String showSubject(Subject subject, Model model) {
		model.addAttribute("topic", new Topic());
		model.addAttribute("topics", subjectService.findAllTopicsBySubject(subject));
		
		model.addAttribute("test", new Test(subject.getName()));
		
		model.addAttribute("forumTopic", new ForumThread());
		model.addAttribute("forumTopics", subjectService.findAllForumTopicsBySubject(subject));
		
		model.addAttribute("isTeacher", userService.hasRoles(Roles.ADMIN)
				|| (userService.hasRoles(Roles.TEACHER) && userService.findMySubjects().contains(subject)));

		return "subjects/subject";
	}

	@PostMapping("like")
	public ResponseEntity<Void> toggleLike(Subject subject) {
		subjectService.toggleLike(subject);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("forum-topics")
	public String processForumTopicCreationForm(Subject subject, @Valid ForumThread forumThread,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("message",
					"Não foi possível criar o tópico para o fórum:\nTítulo ou descrição inválido!");
			return "redirect:/subjects/{subjectId}?page=forum";
		}

		subjectService.saveForumThread(subject, forumThread);

		redirectAttributes.addFlashAttribute("message", "Tópico para o Fórum da disciplina foi criado com sucesso.");

		return "redirect:/subjects/{subjectId}?page=forum";
	}

	@GetMapping("forum-topics/{topicId}")
	public String showForumTopic(Subject subject, @PathVariable("topicId") Long topicId, Model model) {
		ForumThread forumThread = forumThreadService.findById(topicId);

		model.addAttribute("forumTopic", forumThread);
		model.addAttribute("comments", forumThreadService.findAllComments(forumThread));
		model.addAttribute("comment", new Comment());

		return "subjects/forum-topic";
	}

	@PostMapping("forum-topics/{topicId}")
	public String processCommentCreationForm(@PathVariable("topicId") Long topicId, @Valid Comment comment,
			BindingResult bindingResult, RedirectAttributes redirectAttributes, Subject subject, Model model) {
		ForumThread forumThread = forumThreadService.findById(topicId);

		if (bindingResult.hasErrors()) {
			model.addAttribute("comment", comment);
			model.addAttribute("comments", forumThreadService.findAllComments(forumThread));
			model.addAttribute("forumTopic", forumThread);
			return "subjects/forum-topic";
		}

		forumThreadService.saveComment(comment, forumThread);

		redirectAttributes.addFlashAttribute("message", "Comentário adicionado com sucesso.");

		return "redirect:/subjects/{subjectId}/forum-topics/{topicId}";
	}

	@PostMapping
	public String processTopicCreationForm(Subject subject, @Valid Topic topic, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(topic);
			model.addAttribute("topics", subjectService.findAllTopicsBySubject(subject));
			model.addAttribute("page", "topics");
			model.addAttribute("error", true);
			return "subjects/subject";
		}

		topic.setSubject(subject);
		topic.setStatus(UploadStatus.WAITING_FOR_RESPONSE);
		topicService.save(topic);

		redirectAttributes.addFlashAttribute("message",
				"Obrigado pela contribuição! O conteúdo enviado será analisado pela nossa equipe e se tudo estiver certo ele será publicado o mais rápido possível.");

		return "redirect:/subjects/{subjectId}";
	}

	@Autowired
	private TestService testService;

	@PostMapping("test")
	public String processTestCreationForm(Subject subject, @Valid Test test, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(test);
			model.addAttribute("subject", subject);
			model.addAttribute("topics", subjectService.findAllTopicsBySubject(subject));
			model.addAttribute("page", "tests");
			model.addAttribute("topic", new Topic());
			return "subjects/subject";
		}

		test.setSubject(subject);

		testService.save(test);

		return "redirect:/test";
	}

	@ResponseBody
	@GetMapping("forums")
	public List<ForumThread> search(Subject subject, @RequestParam String sort,
			@RequestParam(name = "filter", required = false) List<String> filters,
			@RequestParam(name = "topic", required = false) List<Long> topics) {
		return forumThreadService.findForumThreadBy(subject, sort, filters, topics);
	}

}
