package com.wtoldt.mememagic.domain;

public class PlayerState {

	private String name;
	private int score;
	private boolean ready;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(final int score) {
		this.score = score;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(final boolean ready) {
		this.ready = ready;
	}

	@Override
	public String toString() {
		return "PlayerState [name=" + name + ", score=" + score + ", ready=" + ready + "]";
	}

}
