package com.gutotech.fatecando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gutotech.fatecando.model.Reward;

@Service
public class RewardService {

	@Autowired
	private RestTemplate restTemplate;

	private final String URL = "http://localhost:8081/api/rewards";

	public List<Reward> findAll() {
		ResponseEntity<List<Reward>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Reward>>() {});

		return responseEntity.getBody();
	}

}
