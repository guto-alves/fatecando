package com.gutotech.fatecando.model;

import java.util.Date;
import java.util.Objects;

public class Reward {
	private Long id;
	private RewardType type;
	private Date date;

	public Reward() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RewardType getType() {
		return type;
	}

	public void setType(RewardType type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
		if (!(obj instanceof Reward)) {
			return false;
		}
		Reward other = (Reward) obj;
		return Objects.equals(id, other.id);
	}

}
