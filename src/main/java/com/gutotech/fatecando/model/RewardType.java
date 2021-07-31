package com.gutotech.fatecando.model;

public enum RewardType {
	RIGHT_ANSWER("Resposta Certa"), 
	WRONG_ANSWER("Resposta Errada"),
	TEST_COMPLETED("Teste Concluído"),
	GAME("Game Concluído"),
	CONTRIBUTIONS("Contribuição de Conteúdo");

	private final String displayName;

	private RewardType(String displayName) {
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return displayName;
	}

}
