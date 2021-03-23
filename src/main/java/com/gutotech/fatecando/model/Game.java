package com.gutotech.fatecando.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Game {
	private Long id;

	@NotBlank(message = "Por favor forneça um nome.")
	private String name;

	@Size(min = 2, max = 10, message = "Selecione no mínimo dois tópicos.")
	private List<Topic> topics = new ArrayList<>();

	@DecimalMin(value = "30", message = "O tempo para response deve estar entre 30 segundos e 5 minutos.")
	@DecimalMax(value = "300", message = "O tempo para response deve estar entre 30 segundos e 5 minutos.")
	private long answerTime;

	private GameStatus status;

	@DecimalMin(value = "2", message = "O total de jogadores deve estar entre 2 e 4.")
	@DecimalMax(value = "4", message = "O total de jogadores deve estar entre 2 e 4.")
	private int totalPlayers;

	private List<User> players = new ArrayList<>();

	@DecimalMin(value = "3", message = "O total de rounds deve estar entre 3 e 3.")
	@DecimalMax(value = "6", message = "O total de rounds deve estar entre 6 e 6.")
	private int totalRounds;

	private int currentRound;

	private List<Round> rounds = new ArrayList<>();

	private User createdBy;

	private Date date;

	public Game() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
	}

	public long getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(long answerTime) {
		this.answerTime = answerTime;
	}

	public GameStatus getStatus() {
		return status;
	}

	public void setStatus(GameStatus status) {
		this.status = status;
	}

	public int getTotalPlayers() {
		return totalPlayers;
	}

	public void setTotalPlayers(int totalPlayers) {
		this.totalPlayers = totalPlayers;
	}

	public List<User> getPlayers() {
		return players;
	}

	public void setPlayers(List<User> players) {
		this.players = players;
	}

	public int getTotalRounds() {
		return totalRounds;
	}

	public void setTotalRounds(int totalRounds) {
		this.totalRounds = totalRounds;
	}

	public int getCurrentRound() {
		return currentRound;
	}

	public void setCurrentRound(int currentRound) {
		this.currentRound = currentRound;
	}

	public List<Round> getRounds() {
		return rounds;
	}

	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Game)) {
			return false;
		}
		Game other = (Game) obj;
		return Objects.equals(id, other.id);
	}

}
