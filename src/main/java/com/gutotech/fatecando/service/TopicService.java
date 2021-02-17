package com.gutotech.fatecando.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gutotech.fatecando.model.Topic;

@Service
public class TopicService {

	private RestTemplate restTemplate = new RestTemplate();

	private final String URL = "http://localhost:8081/api/topics";

	public List<Topic> findAll() {
		ResponseEntity<List<Topic>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Topic>>() {
				});

		return responseEntity.getBody();
	}

	public Topic findById(Long id) {
		return restTemplate.getForObject(URL + "/" + id, Topic.class);
	}

}
