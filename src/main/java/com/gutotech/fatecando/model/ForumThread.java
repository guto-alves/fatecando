package com.gutotech.fatecando.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotBlank;

public class ForumThread {
	private Long id;

	@NotBlank(message = "Please provide a valid title.")
	private String title;

	@NotBlank(message = "Please provide a valid description.")
	private String bodyHtml;

	private Date creationDate;

	private int likes;

	private User user;

	private Subject subject;

	private List<Topic> tags = new ArrayList<>();

	private long voteCount;

	private long viewCount;

	private long totalComments;

	private ForumThreadUser me;

	public ForumThread() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBodyHtml() {
		return bodyHtml;
	}

	public void setBodyHtml(String bodyHtml) {
		this.bodyHtml = bodyHtml;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
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

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public List<Topic> getTags() {
		return tags;
	}

	public void setTags(List<Topic> topics) {
		this.tags = topics;
	}

	public long getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(long voteCount) {
		this.voteCount = voteCount;
	}

	public long getViewCount() {
		return viewCount;
	}

	public void setViewCount(long viewCount) {
		this.viewCount = viewCount;
	}

	public long getTotalComments() {
		return totalComments;
	}

	public void setTotalComments(long totalComments) {
		this.totalComments = totalComments;
	}

	public ForumThreadUser getMe() {
		return me;
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
		if (!(obj instanceof ForumThread)) {
			return false;
		}
		ForumThread other = (ForumThread) obj;
		return Objects.equals(id, other.id);
	}

	public class ForumThreadUser {
		private boolean upvoted;
		private boolean downvoted;

		public ForumThreadUser() {
		}

		public boolean isUpvoted() {
			return upvoted;
		}

		public void setUpvoted(boolean upvoted) {
			this.upvoted = upvoted;
		}

		public boolean isDownvoted() {
			return downvoted;
		}

		public void setDownvoted(boolean downvoted) {
			this.downvoted = downvoted;
		}

	}

}
