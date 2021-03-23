package com.gutotech.fatecando.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gutotech.fatecando.model.Game;
import com.gutotech.fatecando.model.RoundAnswer;
import com.gutotech.fatecando.model.Topic;
import com.gutotech.fatecando.service.DisciplineService;
import com.gutotech.fatecando.service.GameService;
import com.gutotech.fatecando.service.TopicService;

@Controller
@RequestMapping("game")
public class GameController {

	@Autowired
	private TopicService topicService;

	@Autowired
	private DisciplineService disciplineService;

	@Autowired
	private GameService gameService;

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
	}

	@GetMapping
	public String initGameCreationForm(Model model) {
		model.addAttribute("game", new Game());
		model.addAttribute("games", gameService.findAll());
		model.addAttribute("topics", topicService.findAll());
		model.addAttribute("disciplines", disciplineService.findAll());
		return "games/games";
	}

	@PostMapping
	public String processGameForm(@Valid Game game, BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute(game);
			model.addAttribute("games", gameService.findAll());
			model.addAttribute("topics", topicService.findAll());
			model.addAttribute("disciplines", disciplineService.findAll());
			return "games/games";
		}

		gameService.save(game);

		return "redirect:/game/waiting-room";
	}

	@PostMapping("join/{id}")
	public String joinGame(@PathVariable Long id) {
		gameService.joinGame(id);
		return "redirect:/game/waiting-room";
	}

	@PostMapping("leave")
	public String leaveGame() {
		gameService.leaveGame();
		return "redirect:/game";
	}

	@GetMapping("waiting-room")
	public String showWaitingRoom(Model model) {
		Game game = gameService.find();

		if (game == null) {
			return "redirect:/game";
		}

		model.addAttribute("game", game);

		return "games/waiting-room";
	}

	@GetMapping("playing")
	public String initPlayingPage(Model model) {
		Game game = gameService.find();

		if (game == null) {
			return "redirect:/game";
		}

		model.addAttribute("game", game);

		return "games/playing";
	}

	// for jQuery methods
	@ResponseBody
	@GetMapping("current")
	public Game getGame() {
		return gameService.find();
	}

	@ResponseBody
	@GetMapping("games")
	public List<Game> getGames() {
		return gameService.findAll();
	}

	@ResponseBody
	@PostMapping("answer/{alternativeId}")
	public RoundAnswer answerGameQuestion(@PathVariable Long alternativeId) {
		return gameService.answerGameQuestion(alternativeId);
	}

}