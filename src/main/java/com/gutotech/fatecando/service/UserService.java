package com.gutotech.fatecando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gutotech.fatecando.model.User;

@Service
public class UserService {

	@Autowired
	private RestTemplate restTemplate;

	private final String URL = "http://localhost:8081/api/users";

	public List<User> findAll() {
		ResponseEntity<List<User>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<User>>() {
				});

		return responseEntity.getBody();
	}

	public User findById(Long id) {
		return restTemplate.getForObject(URL + "/{id}", User.class, id);
	}

	public User register(User user) {
		return restTemplate.postForObject(URL, user, User.class);
	}

	public User login(String email, String password) {
		User user = restTemplate //
				.postForObject(URL + "/login?email={email}&password={password}", //
						null, User.class, email, password);

		if (user != null) {
			restTemplate.getInterceptors().clear();
			restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(email, password));
		}

		return user;
	}

	public User findCurrentUser() {
		ResponseEntity<User> response = restTemplate.getForEntity(URL + "/current", User.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}

		return null;
	}

}
