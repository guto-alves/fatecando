package com.gutotech.fatecando.model;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Course {
	private Long id;

	@NotBlank(message = "Por favor forneça o Nome do Curso")
	private String name;

	@NotBlank(message = "Por favor forneça o Código do Curso")
	private String code;

	private String imageUrl;

	private String description;

	private int totalSemesters;

	@NotNull(message = "Por favor escolha a Instituição do Curso")
	private Institution institution;

	public Course() {
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTotalSemesters() {
		return totalSemesters;
	}

	public void setTotalSemesters(int semesters) {
		this.totalSemesters = semesters;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
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
		return "Course [id=" + id + ", name=" + name + ", code=" + code + "]";
	}

}
