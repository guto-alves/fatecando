package com.gutotech.fatecando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gutotech.fatecando.model.Feedback;
import com.gutotech.fatecando.model.Question;

@Service
public class QuestionService {

	@Autowired
	private RestTemplate restTemplate;

	private final String URL = "http://localhost:8081/api/questions";

	public List<Question> findAll() {
		ResponseEntity<List<Question>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Question>>() {
				});

		return responseEntity.getBody();
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
