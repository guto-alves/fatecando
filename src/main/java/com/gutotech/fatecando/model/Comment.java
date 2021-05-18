package com.gutotech.fatecando.model;

import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.NotBlank;

public class Comment {
	private Long id;

	@NotBlank(message = "Please provide a valid content")
	private String bodyHtml;

	private Date creationDate;

	private User user;

	private boolean accepted;

	private CommentUser me;

	private long voteCount;

	public Comment() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(long voteCount) {
		this.voteCount = voteCount;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public CommentUser getMe() {
		return me;
	}

	public void setMe(CommentUser me) {
		this.me = me;
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
		if (!(obj instanceof Comment)) {
			return false;
		}
		Comment other = (Comment) obj;
		return Objects.equals(id, other.id);
	}

	public class CommentUser {
		private boolean upvoted;
		private boolean downvoted;

		public CommentUser() {
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
