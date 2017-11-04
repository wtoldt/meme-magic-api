package com.wtoldt.mememagic.domain;

import java.util.*;

public class Game {
	private final int id;
    private final LinkedHashMap<String, Player> players = new LinkedHashMap<>();
    private boolean ready = false;
	private GamePhase phase = GamePhase.values()[0];
    private Deque<Image> images; // This is so images can be popped off the deque and discarded after use

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

    public void setImages(final Collection<Image> images) {
        this.images = new ArrayDeque<>(images);
    }

	@Override
	public String toString() {
		return "Game [id=" + id + ", players=" + players + ", ready=" + ready + ", phase=" + phase + "]";
	}
}
