package com.gutotech.fatecando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.gutotech.fatecando.model.Chat;
import com.gutotech.fatecando.model.Message;

@Service
public class ChatService {

	@Autowired
	private CustomRestTemplate restTemplate;

	private static final String URL = "http://localhost:8081/api/chats";

	public List<Chat> findChats() {
		return restTemplate.getForObjects(URL, new ParameterizedTypeReference<List<Chat>>() {
		});
	}

	public List<Message> findMessages(Long chatId) {
		return restTemplate.getForObjects(URL + "/" + chatId, new ParameterizedTypeReference<List<Message>>() {
		});
	}

	public List<Message> findPrivateMessages(String email) {
		return restTemplate.getForObjects(URL + "/messages/private/" + email,
				new ParameterizedTypeReference<List<Message>>() {
				});
	}

}
