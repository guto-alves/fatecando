package com.gutotech.fatecando.model;

import java.util.Objects;

public class Alternative {
	private Long id;

	private String description;

	private String feedback;

	private boolean correct;

	public Alternative() {
	}

	public Alternative(String description, String feedback, boolean correct) {
		this.description = description;
		this.feedback = feedback;
		this.correct = correct;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
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
		if (!(obj instanceof Alternative)) {
			return false;
		}
		Alternative other = (Alternative) obj;
		return Objects.equals(id, other.id);
	}

}
