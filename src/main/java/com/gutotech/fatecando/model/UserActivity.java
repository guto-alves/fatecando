package com.gutotech.fatecando.model;

public class UserActivity {
	private Long id;

	private int rightAnswers;

	private int wrongAnswers;

	private int gameWins;

	private int gameLosses;

	private int testsCompleted;
	
	private int gamesCompleted;

	private int contentUploaded;

	public UserActivity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRightAnswers() {
		return rightAnswers;
	}

	public void setRightAnswers(int rightAnswers) {
		this.rightAnswers = rightAnswers;
	}

	public int getWrongAnswers() {
		return wrongAnswers;
	}

	public void setWrongAnswers(int wrongAnswers) {
		this.wrongAnswers = wrongAnswers;
	}

	public int getGameWins() {
		return gameWins;
	}

	public void setGameWins(int gameWins) {
		this.gameWins = gameWins;
	}

	public int getGameLosses() {
		return gameLosses;
	}

	public void setGameLosses(int gameLosses) {
		this.gameLosses = gameLosses;
	}

	public int getTestsCompleted() {
		return testsCompleted;
	}

	public void setTestsCompleted(int completeTests) {
		this.testsCompleted = completeTests;
	}
	
	public int getGamesCompleted() {
		return gamesCompleted;
	}
	
	public void setGamesCompleted(int gamesCompleted) {
		this.gamesCompleted = gamesCompleted;
	}

	public int getContentUploaded() {
		return contentUploaded;
	}

	public void setContentUploaded(int contentUploaded) {
		this.contentUploaded = contentUploaded;
	}

}
