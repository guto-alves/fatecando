package com.gutotech.fatecando.security;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class HttpEntityUtils {

	public static HttpEntity<Void> createHttpEntity() {
		return new HttpEntity<Void>(createHeaders());
	}

	public static HttpEntity<Object> createHttpEntity(Object request) {
		return new HttpEntity<>(request, createHeaders());
	}

	public static HttpHeaders createHeaders() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setBasicAuth(authentication.getName(), authentication.getCredentials().toString());
			return headers;
		}

		return null;
	}

}
