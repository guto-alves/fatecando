package com.gutotech.fatecando.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;

public class Discipline {
	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	private String code;

	@NotBlank
	private String description;

	@NotBlank
	private String objective;

	private int semester;

	private Set<Topic> topics = new HashSet<>();

	private List<ForumTopic> forum = new ArrayList<>();

	public Discipline() {
	}

	public Discipline(String name, String code, String description, String objetive, int semester) {
		this.name = name;
		this.code = code;
		this.description = description;
		this.objective = objetive;
		this.semester = semester;
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

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public Set<Topic> getTopics() {
		return topics;
	}

	public List<ForumTopic> getForum() {
		return forum;
	}

}
