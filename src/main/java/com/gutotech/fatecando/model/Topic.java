package com.gutotech.fatecando.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Topic {
	private Long id;
	private String name;
	private String description;
	private String contentHtml;

	private List<Question> quiz = new ArrayList<>();

	public Topic() {
	}

	public Topic(String name, String description, String contentHtml) {
		this.name = name;
		this.description = description;
		this.contentHtml = contentHtml;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContentHtml() {
		return contentHtml;
	}

	public void setContentHtml(String contentHtml) {
		this.contentHtml = contentHtml;
	}

	public List<Question> getQuiz() {
		return quiz;
	}

	public void setQuiz(List<Question> quiz) {
		this.quiz = quiz;
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
		if (!(obj instanceof Topic)) {
			return false;
		}
		Topic other = (Topic) obj;
		return Objects.equals(id, other.id);
	}

}
