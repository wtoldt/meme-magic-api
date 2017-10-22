package com.wtoldt.mememagic.domain;

public class Player {

	private final String name;
	private boolean ready = false;
	private int score;

	public Player(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(final boolean ready) {
		this.ready = ready;
	}

	public int getScore() {
		return score;
	}

	public void setScore(final int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", ready=" + ready + ", score=" + score + "]";
	}

}
