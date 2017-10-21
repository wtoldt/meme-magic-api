package com.wtoldt.mememagic.domain;

import java.util.ArrayList;
import java.util.List;

public class Game {

	List<Player> players = new ArrayList<>();

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(final List<Player> players) {
		this.players = players;
	}
}
