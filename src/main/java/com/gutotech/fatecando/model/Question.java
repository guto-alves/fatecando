package com.gutotech.fatecando.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Question {
	private Long id;

	private String description;

	private Set<Alternative> alternatives = new HashSet<>();

	public Question() {
	}

	public Question(String description) {
		this.description = description;
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

	public Set<Alternative> getAlternatives() {
		return alternatives;
	}

	public void setAlternatives(Set<Alternative> alternatives) {
		this.alternatives = alternatives;
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
