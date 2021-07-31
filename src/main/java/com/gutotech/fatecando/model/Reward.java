package com.gutotech.fatecando.model;

import java.util.Date;
import java.util.Objects;

public class Reward {
	private RewardType type;
	private Date date;
	private int score;
	private int coins;

	public Reward() {
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date);
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
		return Objects.equals(date, other.date);
	}

}
