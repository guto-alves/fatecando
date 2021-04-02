package com.gutotech.fatecando.model;

import java.util.Objects;

import javax.validation.constraints.NotBlank;

public class Institution {
	private Long id;

	@NotBlank(message = "Por favor forneça o nome da instituição.")
	private String name;

	private String description;

	public Institution() {
	}

	public Institution(String name, String description) {
		this.name = name;
		this.description = description;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Institution)) {
			return false;
		}
		Institution other = (Institution) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Instituition [id=" + id + ", name=" + name + "]";
	}

}
