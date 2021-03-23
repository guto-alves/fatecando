package com.gutotech.fatecando.model;

public class Feedback {
	private Long alternative;
	private boolean correct;
	private String feedback;

	public Feedback() {
	}

	public Long getAlternative() {
		return alternative;
	}

	public void setAlternative(Long alternative) {
		this.alternative = alternative;
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