package com.gutotech.fatecando.model;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class Course {
	private Long id;

	@NotEmpty(message = "Por favor forneça um nome")
	private String name;

	private int semesters;

	@NotBlank(message = "Por favor forneça uma descrição")
	private String description;

	public Course() {
	}

	public Course(Long id, String name, int semesters) {
		this.id = id;
		this.name = name;
		this.semesters = semesters;
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

	public int getSemesters() {
		return semesters;
	}

	public void setSemesters(int semesters) {
		this.semesters = semesters;
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
		if (!(obj instanceof Course)) {
			return false;
		}
		Course other = (Course) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Course [name=" + name + "]";
	}

}
