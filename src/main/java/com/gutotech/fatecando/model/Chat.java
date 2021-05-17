package com.gutotech.fatecando.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Chat {
	private Long id;
	private boolean pvt;
	private boolean blocked;
	private Message lastMessage;
	private User to;
	private Set<User> users = new HashSet<>();
	private Date creationDate;

	public Chat() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isPvt() {
		return pvt;
	}

	public void setPvt(boolean pvt) {
		this.pvt = pvt;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Message getLastMessage() {
		return lastMessage;
	}

	public void setLastMessage(Message lastMessage) {
		this.lastMessage = lastMessage;
	}

	public User getTo() {
		return to;
	}

	public void setTo(User to) {
		this.to = to;
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
		if (!(obj instanceof Chat)) {
			return false;
		}
		Chat other = (Chat) obj;
		return Objects.equals(id, other.id);
	}

}
