package com.gutotech.fatecando.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.gutotech.fatecando.model.User;
import com.gutotech.fatecando.service.UserService;

@ControllerAdvice
public class UserControllerAdvice {

	@Autowired
	private UserService userService;
	
	@ModelAttribute("user")
	public User getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
			return userService.findCurrentUser();			
		}
		
		return null;
	}
}
