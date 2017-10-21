package com.wtoldt.mememagic.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.wtoldt.mememagic.domain.Game;

@Component
public class GameDao {
	private Map<Integer, Game> games = new HashMap<>();

	public int createGame() {
		final int id = games.size() + 1;
		final Game game = new Game(id);
		games.put(id, game);

		return id;
	}

	public Game getGame(int id) {
		return games.get(id);
	}

}
