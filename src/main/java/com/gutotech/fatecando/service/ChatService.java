package com.gutotech.fatecando.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.gutotech.fatecando.model.Chat;
import com.gutotech.fatecando.model.Message;

@Service
public class ChatService {

	@Autowired
	private CustomRestTemplate restTemplate;

	private final String URL;

	public ChatService(@Value("${fatecando.api.base-url}") String url) {
		URL = url + "/chats";
	}

	public List<Chat> findChats() {
		return restTemplate.getForObjects(URL, new ParameterizedTypeReference<List<Chat>>() {
		});
	}
	
	public Chat findPrivateChat(String email) {
		return restTemplate.getForObject(URL + "/private/{email}", Chat.class, email);
	}

	public List<Message> findMessages(Long chatId) {
		return restTemplate.getForObjects(URL + "/{chatId}/messages", new ParameterizedTypeReference<List<Message>>() {
		}, chatId);
	}

	public List<Message> findPrivateMessages(String email) {
		return restTemplate.getForObjects(URL + "/messages/private/" + email,
				new ParameterizedTypeReference<List<Message>>() {
				});
	}

}
