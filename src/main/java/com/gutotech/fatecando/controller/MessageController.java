package com.gutotech.fatecando.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gutotech.fatecando.model.Message;
import com.gutotech.fatecando.service.ChatService;

@Controller
@RequestMapping("messages")
public class MessageController {

	@GetMapping
	public String showMessagesPage(Model model) {
		model.addAttribute("messages", "Hello world!");
		model.addAttribute("chats", chatService.findChats());
		return "users/messages";
	}

	@Autowired
	private ChatService chatService;

	@ResponseBody
	@GetMapping("{chatId}")
	public List<Message> getChatMessages(@PathVariable Long chatId) {
		return chatService.findMessages(chatId);
	}

	@ResponseBody
	@GetMapping("private/{anotherUserEmail}")
	public List<Message> getPrivateChatMessages(@PathVariable String anotherUserEmail) {
		return chatService.findPrivateMessages(anotherUserEmail);
	}

}
