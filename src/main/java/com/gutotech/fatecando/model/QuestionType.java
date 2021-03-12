package com.gutotech.fatecando.model;

public enum QuestionType {
	QUIZ("Quiz"), TEST("Simulado"), GAME("Game");

	private final String displayName;

	private QuestionType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

}
