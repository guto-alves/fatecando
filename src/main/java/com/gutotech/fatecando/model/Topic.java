package com.gutotech.fatecando.model;

import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.NotBlank;

public class Topic {
	private Long id;

	@NotBlank(message = "O campo Nome é obrigatório")
	private String name;

	@NotBlank(message = "O campo Descrição é obrigatório")
	private String description;

	@NotBlank(message = "O campo Conteúdo é obrigatório")
	private String bodyHtml;

	private boolean required;

	private long itemOrder;

	private UploadStatus status;

	private Subject subject;

	private User createdBy;

	private int likes;

	private Date creationDate;

	private Date updateDate;

	private TopicUser user;

	private Topic previous;

	private Topic next;

	public Topic() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBodyHtml() {
		return bodyHtml;
	}

	public void setBodyHtml(String bodyHtml) {
		this.bodyHtml = bodyHtml;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public long getItemOrder() {
		return itemOrder;
	}

	public void setItemOrder(long itemOrder) {
		this.itemOrder = itemOrder;
	}

	public UploadStatus getStatus() {
		return status;
	}

	public void setStatus(UploadStatus status) {
		this.status = status;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public TopicUser getUser() {
		return user;
	}

	public void setUser(TopicUser user) {
		this.user = user;
	}

	public Topic getPrevious() {
		return previous;
	}

	public void setPrevious(Topic previous) {
		this.previous = previous;
	}

	public Topic getNext() {
		return next;
	}

	public void setNext(Topic next) {
		this.next = next;
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
		if (!(obj instanceof Topic)) {
			return false;
		}
		Topic other = (Topic) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Topic [id=" + id + ", name=" + name + "]";
	}

	public class TopicUser {
		private boolean liked;
		private boolean favorite;
		private String annotation = "";
		private boolean finished;
		private Date finishDate;
		private Review review;

		public TopicUser() {
		}

		public boolean isLiked() {
			return liked;
		}

		public void setLiked(boolean liked) {
			this.liked = liked;
		}

		public boolean isFavorite() {
			return favorite;
		}

		public void setFavorite(boolean favorite) {
			this.favorite = favorite;
		}

		public String getAnnotation() {
			return annotation;
		}

		public void setAnnotation(String annotation) {
			this.annotation = annotation;
		}

		public boolean isFinished() {
			return finished;
		}

		public void setFinished(boolean finished) {
			this.finished = finished;
		}

		public Date getFinishDate() {
			return finishDate;
		}

		public void setFinishDate(Date finishDate) {
			this.finishDate = finishDate;
		}

		public Review getReview() {
			return review;
		}

		public void setReview(Review review) {
			this.review = review;
		}

	}

}
