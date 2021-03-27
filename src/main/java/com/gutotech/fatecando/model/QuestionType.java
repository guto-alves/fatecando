package com.gutotech.fatecando.model;

public enum QuestionType {
	QUIZ("Quiz - Fácil"), TEST("Simulado - Médio"), GAME("Game - Difícil");

	private final String displayName;

	private QuestionType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

}
