package com.wtoldt.mememagic.domain;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private final int id;

	public Game(final int id) {
		this.id = id;
	}

	private List<Player> players = new ArrayList<>();

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(final List<Player> players) {
		this.players = players;
	}

	public int getId() {
		return id;
	}
}
