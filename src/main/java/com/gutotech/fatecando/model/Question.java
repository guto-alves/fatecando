package com.gutotech.fatecando.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class Question {
	private Long id;

	@NotBlank
	private String description;

	private QuestionType type;

	private UploadStatus status;

	@Size(min = 2, max = 6)
	private List<Alternative> alternatives = new ArrayList<>();

	private Topic topic;

	private User user;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date creationDate;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date updateDate;

	public Question() {
	}

	public Question(String description, QuestionType type, UploadStatus status, List<Alternative> alternatives,
			Topic topic, User user) {
		this.description = description;
		this.type = type;
		this.status = status;
		this.topic = topic;
		this.user = user;
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

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	public UploadStatus getStatus() {
		return status;
	}

	public void setStatus(UploadStatus status) {
		this.status = status;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Alternative> getAlternatives() {
		return alternatives;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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
		if (!(obj instanceof Question)) {
			return false;
		}
		Question other = (Question) obj;
		return Objects.equals(id, other.id);
	}

}
