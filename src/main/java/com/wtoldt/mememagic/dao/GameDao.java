package com.wtoldt.mememagic.dao;

import com.wtoldt.mememagic.domain.Game;
import com.wtoldt.mememagic.exception.NoSuchGameException;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GameDao {
	private final Map<Integer, Game> games = new HashMap<>();

	public int createGame() {
		final int id = games.size() + 1;
		final Game game = new Game(id);
		games.put(id, game);

		return id;
	}

	public Game getGame(int id) throws NoSuchGameException {
		Game game = games.get(id);
		if (game == null) {
			throw new NoSuchGameException(id);
		}
		return game;
	}

}
