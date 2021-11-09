package com.gutotech.fatecando.model;

import java.util.Date;

public class Review {
	private Integer stars;

	private String content;

	private Date date;
	
	private User user;

	public Review() {
	}

	public Review(Integer stars, String content) {
		this.stars = stars;
		this.content = content;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(Integer rating) {
		this.stars = rating;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String comment) {
		this.content = comment;
	}

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
