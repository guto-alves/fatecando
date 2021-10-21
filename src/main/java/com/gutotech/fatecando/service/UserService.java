package com.gutotech.fatecando.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.gutotech.fatecando.model.OAuth2AccessToken;
import com.gutotech.fatecando.model.PasswordForm;
import com.gutotech.fatecando.model.Question;
import com.gutotech.fatecando.model.Reward;
import com.gutotech.fatecando.model.Role;
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
		HttpHeaders headers = new HttpHeaders();
		headers.setBasicAuth("fatecando", "123");

		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject(URL, new HttpEntity<>(user, headers), User.class);
	}

	public User login(String email, String password) {
		return restTemplate //
				.postForObject(URL + "/login?email={email}&password={password}", null, User.class, email, password);
	}
	
	public void updatePassword(PasswordForm passwordForm) {
		restTemplate.put(URL + "/password", passwordForm);
	}

	public String authenticate(String email, String password) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setBasicAuth("fatecando", "fatecando");

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("username", email);
		map.add("password", password);
		map.add("grant_type", "password");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		OAuth2AccessToken result = new RestTemplate().postForObject(URL.split("/api")[0] + "/oauth/token", request,
				OAuth2AccessToken.class);

		return result.getAccess_token();
	}

	public List<Role> getUserRoles(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(token);

		return new RestTemplate().exchange(URL + "/me/roles", HttpMethod.GET, new HttpEntity<>(null, headers),
				new ParameterizedTypeReference<List<Role>>() {
				}).getBody();
	}

	public User findCurrentUser() {
		return restTemplate.getForObject(URL + "/me", User.class);
	}

	public void update(User user) {
		restTemplate.put(URL + "/{id}", user, user.getId());

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
		updatedAuthorities.clear();
		updatedAuthorities.addAll(getUserRoles(auth.getCredentials().toString()));
		
		Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(),
				updatedAuthorities);

		SecurityContextHolder.getContext().setAuthentication(newAuth);
	}

	public List<Subject> findMySubjects() {
		return restTemplate.getForObjects(URL + "/me/subjects", new ParameterizedTypeReference<List<Subject>>() {
		});
	}

	public List<Subject> findMySubjects(User user) {
		return restTemplate.getForObjects(URL + "/{id}/subjects", new ParameterizedTypeReference<List<Subject>>() {
		}, user.getId());
	}

	public List<Topic> findMyTopics() {
		return restTemplate.getForObjects(URL + "/me/topics", new ParameterizedTypeReference<List<Topic>>() {
		});
	}

	public List<Question> findMyQuestions() {
		return restTemplate.getForObjects(URL + "/me/questions", new ParameterizedTypeReference<List<Question>>() {
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

	public List<Reward> findMyRewards() {
		return restTemplate.getForObjects(URL + "/me/rewards", new ParameterizedTypeReference<List<Reward>>() {
		});
	}

	public boolean hasRoles(String... roles) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		return roles != null && auth != null
				&& auth.getAuthorities().stream().anyMatch(a -> Arrays.asList(roles).contains(a.getAuthority()));
	}

	public boolean isLoggedIn() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
			return true;
		}

		return false;
	}
}
