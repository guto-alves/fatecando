package com.gutotech.fatecando.model;

import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.NotBlank;

public class TicketResponse {
	private Long id;

	@NotBlank
	private String content;

	private User user;

	private Date date;

	public TicketResponse() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TicketResponse)) {
			return false;
		}
		TicketResponse other = (TicketResponse) obj;
		return Objects.equals(id, other.id);
	}

}
