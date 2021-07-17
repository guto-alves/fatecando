package com.gutotech.fatecando.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.gutotech.fatecando.model.Notification;
import com.gutotech.fatecando.model.User;
import com.gutotech.fatecando.service.NotificationsService;
import com.gutotech.fatecando.service.UserService;

@ControllerAdvice
public class UserControllerAdvice {

	@Autowired
	private UserService userService;
	
	@Autowired
	private NotificationsService notificationService;
	
	@ModelAttribute("currentUser")
	public User getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
			return userService.findCurrentUser();			
		}
		
		return null;
	}
	
	@ModelAttribute("notifications")
	public List<Notification> getNotifications() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
			return notificationService.findAll();			
		}
		
		return null;
	}
}
