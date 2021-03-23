package com.gutotech.fatecando.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Round {
	private Long id;

	private Question question;

	private long startTime;

	private int secondsLeft;

	private List<RoundAnswer> answers = new ArrayList<>();

	public Round() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public int getSecondsLeft() {
		return secondsLeft;
	}

	public void setSecondsLeft(int secondsLeft) {
		this.secondsLeft = secondsLeft;
	}

	public List<RoundAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<RoundAnswer> answers) {
		this.answers = answers;
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
		if (!(obj instanceof Round)) {
			return false;
		}
		Round other = (Round) obj;
		return Objects.equals(id, other.id);
	}

}
