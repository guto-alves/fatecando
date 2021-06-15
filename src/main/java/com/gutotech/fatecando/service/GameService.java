package com.gutotech.fatecando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.gutotech.fatecando.model.Game;
import com.gutotech.fatecando.model.RoundAnswer;

@Service
public class GameService {

	private final String URL;

	@Autowired
	private CustomRestTemplate restTemplate;

	public GameService(@Value("${fatecando.api.base-url}") String url) {
		URL = url + "/games";
	}

	public List<Game> findAll() {
		return restTemplate.getForObjects(URL, new ParameterizedTypeReference<List<Game>>() {
		});
	}

	public Game find() {
		return restTemplate.getForObject(URL + "/playing", Game.class);
	}

	public Game save(Game game) {
		return restTemplate.postForObject(URL, game, Game.class);
	}

	public void joinGame(Long id) {
		restTemplate.put(URL + "/" + id, null);
	}

	public void leaveGame() {
		restTemplate.delete(URL);
	}

	public RoundAnswer answerGameQuestion(Long chosenAlternativeId) {
		return restTemplate.postForObject(URL + "/answer/" + chosenAlternativeId, null, RoundAnswer.class);
	}

}
