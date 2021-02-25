package com.gutotech.fatecando.model;

public class UserActivity {
	private Long id;

	private int rightAnswers;

	private int wrongAnswers;

	private int gameWins;

	private int gameLosses;

	private int completeTests;

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

	public int getCompleteTests() {
		return completeTests;
	}

	public void setCompleteTests(int completeTests) {
		this.completeTests = completeTests;
	}

	public int getContentUploaded() {
		return contentUploaded;
	}

	public void setContentUploaded(int contentUploaded) {
		this.contentUploaded = contentUploaded;
	}

}
