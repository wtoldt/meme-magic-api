package com.wtoldt.mememagic.domain;

import java.util.ArrayList;
import java.util.List;

public class GameState {

	private final List<String> players = new ArrayList<>();
	private boolean ready = false;

	public boolean isReady() {
		return ready;
	}

	public void setReady(final boolean ready) {
		this.ready = ready;
	}

	public List<String> getPlayers() {
		return players;
	}

	@Override
	public String toString() {
		return "GameState [players=" + players + ", ready=" + ready + "]";
	}

}
