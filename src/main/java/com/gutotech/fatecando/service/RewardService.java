package com.gutotech.fatecando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.gutotech.fatecando.model.Reward;

@Service
public class RewardService {

	@Autowired
	private CustomRestTemplate restTemplate;

	private final String URL = "http://localhost:8081/api/rewards";

	public List<Reward> findAll() {
		return restTemplate.getForObjects(URL, new ParameterizedTypeReference<List<Reward>>() {});
	}

}
