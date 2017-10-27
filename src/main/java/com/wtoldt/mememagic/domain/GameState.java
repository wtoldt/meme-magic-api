package com.wtoldt.mememagic.domain;

import java.util.ArrayList;
import java.util.List;

public class GameState {

	private final List<String> players = new ArrayList<>();
	private boolean ready = false;
	private int phase = 1;

	public boolean isReady() {
		return ready;
	}

	public void setReady(final boolean ready) {
		this.ready = ready;
	}

	public List<String> getPlayers() {
		return players;
	}

	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	@Override
	public String toString() {
		return "GameState [players=" + players + ", ready=" + ready + ", phase=" + phase + "]";
	}	

}
