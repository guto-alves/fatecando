package com.gutotech.fatecando.model;

import java.util.List;
import java.util.Objects;

public class QuestionType {
	public static final QuestionType QUIZ = new QuestionType("QUIZ");
	public static final QuestionType TEST = new QuestionType("TEST");
	public static final QuestionType GAME = new QuestionType("GAME");

	private String name;

	public QuestionType() {
	}

	public QuestionType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static List<QuestionType> getAllTypes() {
		return List.of(QUIZ, TEST, GAME);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof QuestionType)) {
			return false;
		}
		QuestionType other = (QuestionType) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return name;
	}

}
