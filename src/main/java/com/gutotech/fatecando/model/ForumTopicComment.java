package com.gutotech.fatecando.model;

import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.NotBlank;

public class ForumTopicComment {
	private Long id;

	@NotBlank(message = "Please provide a valid content")
	private String content;

	private Date date;
	private int likes;
	private User user;
	private ForumTopic forumTopic;

	public ForumTopicComment() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ForumTopic getForumTopic() {
		return forumTopic;
	}

	public void setForumTopic(ForumTopic forumTopic) {
		this.forumTopic = forumTopic;
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
		if (!(obj instanceof ForumTopicComment)) {
			return false;
		}
		ForumTopicComment other = (ForumTopicComment) obj;
		return Objects.equals(id, other.id);
	}

}
