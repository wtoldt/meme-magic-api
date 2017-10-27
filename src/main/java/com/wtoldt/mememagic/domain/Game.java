package com.wtoldt.mememagic.domain;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private final int id;
	private List<Player> players = new ArrayList<>();
	private boolean ready = false;
	private int phase = 1;

	public Game(final int id) {
		this.id = id;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(final List<Player> players) {
		this.players = players;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(final boolean ready) {
		this.ready = ready;
	}

	public int getId() {
		return id;
	}

	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", players=" + players + ", ready=" + ready + ", phase=" + phase + "]";
	}
}
