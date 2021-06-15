package com.gutotech.fatecando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.gutotech.fatecando.model.Reward;

@Service
public class RewardService {

	@Autowired
	private CustomRestTemplate restTemplate;

	private final String URL;

	public RewardService(@Value("${fatecando.api.base-url}") String url) {
		URL = url + "/rewards";
	}

	public List<Reward> findAll() {
		return restTemplate.getForObjects(URL, new ParameterizedTypeReference<List<Reward>>() {
		});
	}

}
