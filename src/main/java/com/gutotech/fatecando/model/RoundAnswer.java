package com.gutotech.fatecando.model;

import java.util.Objects;

public class RoundAnswer {
	private Long id;

	private User user;

	private Feedback feedback = new Feedback();

	public RoundAnswer() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
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
		if (!(obj instanceof RoundAnswer)) {
			return false;
		}
		RoundAnswer other = (RoundAnswer) obj;
		return Objects.equals(id, other.id);
	}

}
