package com.gutotech.fatecando.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Test {
	private Long id;

	@NotBlank(message = "Por favor forneça um nome.")
	private String name;
	
	private long startTime;

	private User user;

	private Subject subject;

	@Size(min = 2, message = "Por favor selecione no mínimo dois tópicos.")
	private List<Topic> topics = new ArrayList<>();

	private List<Question> questions = new ArrayList<>();

	public Test() {
	}

	public Test(String name) {
		this.name = name;
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
	
	public long getStartTime() {
		return startTime;
	}
	
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
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
		if (!(obj instanceof Test)) {
			return false;
		}
		Test other = (Test) obj;
		return Objects.equals(id, other.id);
	}

}
