package com.gutotech.fatecando.model;

import java.util.Date;
import java.util.Objects;

public class Notification {
	private Long id;

	private String text;

	private String link;

	private boolean isRead;

	private Date date;

	public Notification() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date creationDate) {
		this.date = creationDate;
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
		if (!(obj instanceof Notification)) {
			return false;
		}
		Notification other = (Notification) obj;
		return Objects.equals(id, other.id);
	}

}
