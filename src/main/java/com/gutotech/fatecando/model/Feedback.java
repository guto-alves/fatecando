package com.gutotech.fatecando.model;

public class Feedback {
	private boolean correct;

	private String feedback;

	public Feedback() {
	}

	public Feedback(String feedback, boolean correct) {
		this.feedback = feedback;
		this.correct = correct;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

}