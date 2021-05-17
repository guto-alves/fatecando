package com.gutotech.fatecando.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.gutotech.fatecando.model.User;
import com.gutotech.fatecando.service.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();

		if (name == null || name.trim().isEmpty()) {
			throw new AuthenticationCredentialsNotFoundException("Username was null or empty.");
		}
		if (password == null || password.trim().isEmpty()) {
			throw new AuthenticationCredentialsNotFoundException("Password was null or empty.");
		}

		User user = userService.login(name, password);

		if (user == null) {
			throw new BadCredentialsException("Bad credentials for user " + name);
		}

		return new UsernamePasswordAuthenticationToken(name, password, user.getRoles());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
