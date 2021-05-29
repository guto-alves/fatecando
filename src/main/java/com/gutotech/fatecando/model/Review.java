package com.gutotech.fatecando.model;

public class Review {
	private Integer stars;

	private String content;

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

}
