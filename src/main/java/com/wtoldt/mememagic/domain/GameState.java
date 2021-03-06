package com.wtoldt.mememagic.domain;

import java.util.ArrayList;
import java.util.List;

public class GameState {

	private final List<PlayerState> players = new ArrayList<>();
	private boolean ready = false;
	private GamePhase phase = GamePhase.values()[0];

	public boolean isReady() {
		return ready;
	}

	public void setReady(final boolean ready) {
		this.ready = ready;
	}

	public List<PlayerState> getPlayers() {
		return players;
	}

	public GamePhase getPhase() {
		return phase;
	}

	public void setPhase(final GamePhase phase) {
		this.phase = phase;
	}

	@Override
	public String toString() {
		return "GameState [players=" + players + ", ready=" + ready + ", phase=" + phase + "]";
	}

}
