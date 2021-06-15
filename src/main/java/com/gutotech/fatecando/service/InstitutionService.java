package com.gutotech.fatecando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.gutotech.fatecando.model.Institution;

@Service
public class InstitutionService {

	@Autowired
	private CustomRestTemplate restTemplate;

	private final String URL;

	public InstitutionService(@Value("${fatecando.api.base-url}") String url) {
		URL = url + "/institutions";
	}

	public List<Institution> findAll() {
		return restTemplate.getForObjects(URL, new ParameterizedTypeReference<List<Institution>>() {});
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
