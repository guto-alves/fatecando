package com.gutotech.fatecando.security;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;

public class HttpEntityUtils {

	public static HttpEntity<Void> createHttpEntity() {
		return new HttpEntity<Void>(createHeaders());
	}

	public static HttpEntity<Object> createHttpEntity(Object request) {
		return new HttpEntity<Object>(request, createHeaders());
	}

	private static HttpHeaders createHeaders() {
		HttpHeaders headers = new HttpHeaders();

		headers.setBasicAuth(SecurityContextHolder.getContext().getAuthentication().getName(),
				SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());

		return headers;
	}

}
