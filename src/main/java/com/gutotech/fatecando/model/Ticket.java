package com.gutotech.fatecando.model;

import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Ticket {

	public enum Status {
		WAITING("Aguardando resposta"), ANSWERED("Respondido"), CLOSED("Fechado");

		private final String displayName;

		Status(String displayName) {
			this.displayName = displayName;
		}

		public String getDisplayName() {
			return displayName;
		}
	}

	public enum Category {
		BUG("Bug"), QUESTION("Dúvidas"), SUGGESTION("Sugestões"), OTHERS("Outros");

		private final String displayName;

		Category(String displayName) {
			this.displayName = displayName;
		}

		public String getDisplayName() {
			return displayName;
		}

	}

	private Long id;

	@Size(max = 30)
	@NotBlank(message = "Campo Título é obrigatório")
	private String title;

	@NotBlank(message = "Descrição obrigatória")
	private String description;

	private User user;

	private Status status;

	private Category category;

	private Date creationDate;

	private Date updateDate;

	public Ticket() {
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
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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
		if (!(obj instanceof Ticket)) {
			return false;
		}
		Ticket other = (Ticket) obj;
		return Objects.equals(id, other.id);
	}

}
