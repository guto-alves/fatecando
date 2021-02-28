package com.gutotech.fatecando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gutotech.fatecando.model.Institution;

@Service
public class InstitutionService {

	@Autowired
	private RestTemplate restTemplate;

	private final String URL = "http://localhost:8081/api/institutions";

	public List<Institution> findAll() {
		ResponseEntity<List<Institution>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Institution>>() {
				});

		return responseEntity.getBody();
	}

	public Institution findById(Long id) {
		return restTemplate.getForObject(URL + "/{id}", Institution.class, id);
	}

	public Institution save(Institution institution) {
		return restTemplate.postForObject(URL, institution, Institution.class);
	}

	public void update(Institution institution) {
		restTemplate.put(URL + "/{id}", institution, institution.getId());
	}

}
