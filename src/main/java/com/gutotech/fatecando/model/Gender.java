package com.gutotech.fatecando.model;

public enum Gender {
	MALE("Masculino"), FEMALE("Femino");

	private final String displayName;

	private Gender(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

}
