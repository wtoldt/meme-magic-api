package com.wtoldt.mememagic.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtoldt.mememagic.dao.GameDao;
import com.wtoldt.mememagic.domain.Game;
import com.wtoldt.mememagic.domain.GameState;
import com.wtoldt.mememagic.domain.Player;
import com.wtoldt.mememagic.exception.NoSuchGameException;

@Service
public class GameService {
	private final GameDao gameDao;

	@Autowired
	public GameService(final GameDao gameDao) {
		this.gameDao = gameDao;
	}

	public int createGame() {
		return gameDao.createGame();
	}

	public Game getGame(final int id) {
		return gameDao.getGame(id);
	}

	public void joinGame(final int gameId, final String playerName) throws NoSuchGameException {
		final Game game = gameDao.getGame(gameId);
		if (game == null) {
			throw new NoSuchGameException(gameId);
		}
		// TODO: if game not joinable, throw exception
		// TODO: make sure player isn't already in game

		final Player player = new Player(playerName);
		game.getPlayers().add(player);
	}

	public static GameState getGameState(final Game game) {
		final GameState gameState = new GameState();

		gameState.getPlayers().addAll(game.getPlayers().stream().map(Player::getName).collect(Collectors.toList()));
		gameState.setReady(game.isReady());

		return gameState;
	}
}
