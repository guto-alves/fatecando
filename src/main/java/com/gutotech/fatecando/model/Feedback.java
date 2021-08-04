package com.gutotech.fatecando.model;

import javax.validation.constraints.NotBlank;

public class Feedback {
	private Long id;
	
	@NotBlank
	private String title;
	
	private String description;
	
	private boolean correct;

	private Alternative alternative;

	public Feedback() {
	}

	public Feedback(boolean correct) {
		this.correct = correct;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public Alternative getAlternative() {
		return alternative;
	}

	public void setAlternative(Alternative alternative) {
		this.alternative = alternative;
	}

}