package com.gutotech.fatecando.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User {
	private Long id;

	@NotBlank(message = "Por favor preencha seu nome completo.")
	private String fullName;

	@NotBlank(message = "Por favor digite uma email válido.")
	private String email;

	@NotBlank(message = "Por favor digite uma senha válida.")
	private String password;

	private boolean enabled;

	private Gender gender;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date birthDate;

	private UserActivity userActivity;

	private Course course;

	private Date creationDate;

	private Date lastLogin;

	private List<Role> roles = new ArrayList<>();

	public User() {
	}

	public User(String fullName, String email, String password, Gender gender, Date birthDate, Course course) {
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.birthDate = birthDate;
		this.course = course;
	}

	public User(String fullName, String email, String password, Gender gender, Date birthDate, Course course,
			List<Role> roles) {
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.birthDate = birthDate;
		this.course = course;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public UserActivity getUserActivity() {
		return userActivity;
	}

	public void setUserActivity(UserActivity userActivity) {
		this.userActivity = userActivity;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
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
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

}
