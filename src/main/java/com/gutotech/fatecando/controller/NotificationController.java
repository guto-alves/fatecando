package com.gutotech.fatecando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gutotech.fatecando.service.NotificationsService;

@Controller
@RequestMapping("notifications")
public class NotificationController {

	@Autowired
	private NotificationsService service;

	@GetMapping
	public String showNotificationsPage() {
		return "users/notifications";
	}

	@ResponseBody
	@PostMapping("{id}/read")
	public void toggleNotificationRead(@PathVariable Long id) {
		service.toggleRead(id);
	}
	
	@ResponseBody
	@PostMapping("readall")
	public void readAllNotifications() {
		service.readAll();
	}
}
