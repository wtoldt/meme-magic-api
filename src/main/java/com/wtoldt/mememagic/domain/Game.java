package com.wtoldt.mememagic.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Game {
	private final int id;
    private final LinkedHashMap<String, Player> players = new LinkedHashMap<>();
    private boolean ready = false;
	private GamePhase phase = GamePhase.values()[0];

	public Game(final int id) {
		this.id = id;
	}

	public List<Player> getPlayers() {
        return new ArrayList<>(players.values());
    }

    public void addPlayer(final Player player) {
        players.put(player.getName(), player);
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

	public void setPhase(final GamePhase gamePhase) {
		this.phase = gamePhase;
	}

	public GamePhase getPhase() {
		return phase;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", players=" + players + ", ready=" + ready + ", phase=" + phase + "]";
	}
}
