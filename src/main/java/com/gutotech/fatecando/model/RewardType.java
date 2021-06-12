package com.gutotech.fatecando.model;

public enum RewardType {
	RIGHT_ANSWER("Resposta Certa", 5, 100), 
	WRONG_ANSWER("Resposta Errada", -15, 0),
	TEST_SUCCESS("Teste Finalizado", 30, 500), 
	TEST_FAILURE("Falha no Teste", -30, 0), 
	GAME("Game", 60, 1000);

	private String displayName;
	private final int score;
	private final int coins;

	private RewardType(String displayName, int score, int coins) {
		this.displayName = displayName;
		this.score = score;
		this.coins = coins;
	}
	
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getScore() {
		return score;
	}

	public int getCoins() {
		return coins;
	}

}
