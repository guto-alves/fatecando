package com.gutotech.fatecando.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Discipline {
	private Long id;
	private String name;
	private String code;
	private String description;
	private String objetive;
	private int semestre;

	private List<Topic> topics = new ArrayList<>();

	public Discipline() {
	}

	public Discipline(String name, String code, String description, String objetive, int semestre) {
		this.name = name;
		this.code = code;
		this.description = description;
		this.objetive = objetive;
		this.semestre = semestre;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getObjetive() {
		return objetive;
	}

	public void setObjetive(String objetive) {
		this.objetive = objetive;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public List<Topic> getTopics() {
		return topics;
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
		if (!(obj instanceof Discipline)) {
			return false;
		}
		Discipline other = (Discipline) obj;
		return Objects.equals(id, other.id);
	}

}
