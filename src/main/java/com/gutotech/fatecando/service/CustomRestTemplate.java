package com.gutotech.fatecando.service;

import java.net.URI;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.gutotech.fatecando.security.HttpEntityUtils;

@Component
public class CustomRestTemplate {
	private final RestTemplate restTemplate = new RestTemplate();

	// GET
	public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) {
		return restTemplate
				.exchange(url, HttpMethod.GET, HttpEntityUtils.createHttpEntity(), responseType, uriVariables)
				.getBody();
	}

	public <T> T getForObject(URI url, Class<T> responseType) {
		return restTemplate.exchange(url, HttpMethod.GET, HttpEntityUtils.createHttpEntity(), responseType).getBody();
	}

	public <T> List<T> getForObjects(String url, ParameterizedTypeReference<List<T>> responseType,
			Object... uriVariables) {
		return restTemplate
				.exchange(url, HttpMethod.GET, HttpEntityUtils.createHttpEntity(), responseType, uriVariables)
				.getBody();
	}

	// POST
	public <T> T postForObject(URI url, Object request, Class<T> responseType) {
		return restTemplate.exchange(url, HttpMethod.POST, HttpEntityUtils.createHttpEntity(request), responseType)
				.getBody();
	}

	public <T> T postForObject(String url, Object request, Class<T> responseType, Object... uriVariables) {
		return restTemplate
				.exchange(url, HttpMethod.POST, HttpEntityUtils.createHttpEntity(request), responseType, uriVariables)
				.getBody();
	}

	// PUT
	public void put(String url, @Nullable Object request, Object... uriVariables) {
		restTemplate.exchange(url, HttpMethod.PUT, HttpEntityUtils.createHttpEntity(request), Void.class, uriVariables);
	}

	public void put(URI url, @Nullable Object request) {
		restTemplate.exchange(url, HttpMethod.PUT, HttpEntityUtils.createHttpEntity(request), Void.class);
	}

	// DELETE
	public void delete(String url, Object... uriVariables) {
		restTemplate.exchange(url, HttpMethod.DELETE, HttpEntityUtils.createHttpEntity(), Void.class, uriVariables);
	}

	public void delete(URI url) {
		restTemplate.exchange(url, HttpMethod.DELETE, HttpEntityUtils.createHttpEntity(), Void.class);
	}

}
