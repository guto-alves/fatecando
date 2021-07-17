package com.gutotech.fatecando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.gutotech.fatecando.model.Notification;

@Service
public class NotificationsService {

	@Autowired
	private CustomRestTemplate restTemplate;

	private final String URL;

	public NotificationsService(@Value("${fatecando.api.base-url}") String url) {
		URL = url + "/notifications";
	}

	public List<Notification> findAll() {
		return restTemplate.getForObjects(URL, new ParameterizedTypeReference<List<Notification>>() {
		});
	}

	public void toggleRead(Long id) {
		restTemplate.put(URL + "/{id}/read", null, id);
	}

	public void readAll() {
		restTemplate.put(URL + "/readall", null);
	}

}
