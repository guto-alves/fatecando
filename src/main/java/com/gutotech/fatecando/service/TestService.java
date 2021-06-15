package com.gutotech.fatecando.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gutotech.fatecando.model.Test;

@Service
public class TestService {

	@Autowired
	private CustomRestTemplate restTemplate;

	private final String URL;

	public TestService(@Value("${fatecando.api.base-url}") String url) {
		URL = url + "/tests";
	}

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
