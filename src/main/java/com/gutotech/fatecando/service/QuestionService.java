package com.gutotech.fatecando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.gutotech.fatecando.model.Feedback;
import com.gutotech.fatecando.model.Question;

@Service
public class QuestionService {

	@Autowired
	private CustomRestTemplate restTemplate;

	private final String URL;

	public QuestionService(@Value("${fatecando.api.base-url}") String url) {
		URL = url + "/questions";
	}
	
	public List<Question> findAll() {
		return restTemplate.getForObjects(URL, new ParameterizedTypeReference<List<Question>>() {});
	}

	public Question findById(Long id) {
		return restTemplate.getForObject(URL + "/" + id, Question.class);
	}

	public Question save(Question question) {
		return restTemplate.postForObject(URL, question, Question.class);
	}

	public void update(Question question) {
		restTemplate.put(URL + "/{id}", question, question.getId());
	}

	public Question upload(Question question) {
		return restTemplate.postForObject(URL + "/upload", question, Question.class);
	}

	public Feedback answer(Long question, Long alternative) {
		return restTemplate.postForObject(URL + "/{question}/answer/{alternative}", null, Feedback.class, question,
				alternative);
	}

}
