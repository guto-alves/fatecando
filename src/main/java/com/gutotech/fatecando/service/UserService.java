package com.gutotech.fatecando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gutotech.fatecando.model.Subject;
import com.gutotech.fatecando.model.Topic;
import com.gutotech.fatecando.model.User;

@Service
public class UserService {

	@Autowired
	private CustomRestTemplate restTemplate;

	private final String URL;

	public UserService(@Value("${fatecando.api.base-url}") String url) {
		URL = url + "/users";
	}

	public List<User> findAll() {
		return restTemplate.getForObjects(URL, new ParameterizedTypeReference<List<User>>() {
		});
	}

	public List<User> getRanking() {
		return restTemplate.getForObjects(URL + "/ranking", new ParameterizedTypeReference<List<User>>() {
		});
	}

	public List<User> searchByNameOrEmail(String filter) {
		return restTemplate.getForObjects(URL + "/search?filter=" + filter,
				new ParameterizedTypeReference<List<User>>() {
				});
	}

	public User findById(Long id) {
		return restTemplate.getForObject(URL + "/{id}", User.class, id);
	}

	public User register(User user) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject(URL, user, User.class);
	}

	public User login(String email, String password) {
		return restTemplate //
				.postForObject(URL + "/login?email={email}&password={password}", null, User.class, email, password);
	}

	public User findCurrentUser() {
		return restTemplate.getForObject(URL + "/me", User.class);
	}

	public void update(User user) {
		restTemplate.put(URL + "/{id}", user, user.getId());
	}

	public List<Topic> findAllTopics() {
		return restTemplate.getForObjects(URL + "/me/topics", new ParameterizedTypeReference<List<Topic>>() {
		});
	}

	public List<Subject> findSubjectsAccessed() {
		return restTemplate.getForObjects(URL + "/me/subjects/last-accessed",
				new ParameterizedTypeReference<List<Subject>>() {
				});
	}

	public List<Topic> findFavoriteTopics() {
		return restTemplate.getForObjects(URL + "/me/topics/favorites", new ParameterizedTypeReference<List<Topic>>() {
		});
	}

	public List<Topic> findAnnotatedTopics() {
		return restTemplate.getForObjects(URL + "/me/topics/annotated", new ParameterizedTypeReference<List<Topic>>() {
		});
	}

}
