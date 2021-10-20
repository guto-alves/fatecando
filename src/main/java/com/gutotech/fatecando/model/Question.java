package com.gutotech.fatecando.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Question {
	private Long id;

	@NotBlank(message = "Por favor forneça uma pergunta válida.")
	private String description;

	@Size(min = 1, message = "A questão precisa ser de pelo menos um dos tipos (QUIZ, TEST OU GAME).")
	private List<QuestionType> types = new ArrayList<>();

	private UploadStatus status;

	@Size(min = 2, max = 6, message = "Quantidade de alternativas deve ser no mínino 2 e no máximo 6.")
	private List<Alternative> alternatives = new ArrayList<>();

	private Topic topic;

	private User user;

	private Date creationDate;

	private Date updateDate;

	public Question() {
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

	public List<QuestionType> getTypes() {
		return types;
	}

	public void setTypes(List<QuestionType> types) {
		this.types = types;
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
