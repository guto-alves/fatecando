package com.gutotech.fatecando.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gutotech.fatecando.model.Test;

@Service
public class TestService {

	@Autowired
	private RestTemplate restTemplate;

	private final String URL = "http://localhost:8081/api/tests";

	public Test find() {
		return restTemplate.getForObject(URL, Test.class);
	}

	public Test save(Test test) {
		return restTemplate.postForObject(URL, test, Test.class);
	}

	public void finish() {
		restTemplate.delete(URL);
	}

}
