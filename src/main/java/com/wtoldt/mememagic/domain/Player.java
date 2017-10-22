package com.wtoldt.mememagic.domain;

public class Player {
	private final String name;

	//TODO: dont let players set this (@null?)
	private int score;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(final int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", score=" + score + "]";
	}

}
