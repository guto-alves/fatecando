package com.gutotech.fatecando.model;

import java.util.Objects;

import javax.validation.constraints.NotBlank;

public class Alternative {
	private Long id;

	@NotBlank
	private String description;

	private Feedback feedback;

	public Alternative() {
	}

	public Alternative(String description, Feedback feedback) {
		this.description = description;
		this.feedback = feedback;
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
		if (!(obj instanceof Alternative)) {
			return false;
		}
		Alternative other = (Alternative) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Alternative [id=" + id + ", description=" + description + "]";
	}

}