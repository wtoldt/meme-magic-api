package com.wtoldt.mememagic.service;

import com.wtoldt.mememagic.dao.GameDao;
import com.wtoldt.mememagic.domain.Game;
import com.wtoldt.mememagic.domain.GamePhase;
import com.wtoldt.mememagic.domain.Player;
import com.wtoldt.mememagic.exception.GameNotJoinableException;
import com.wtoldt.mememagic.exception.NoSuchGameException;
import com.wtoldt.mememagic.exception.NoSuchPlayerException;
import com.wtoldt.mememagic.exception.PlayerAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

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

	public Game getGame(final int id) throws NoSuchGameException {
		return gameDao.getGame(id);
	}

	public void joinGame(final int gameId, final String playerName)
			throws NoSuchGameException, GameNotJoinableException, PlayerAlreadyExistsException {

		final Game game = gameDao.getGame(gameId);
		validateGameisJoinable(game);
		validatePlayerJoinGame(game, playerName);

		final Player player = new Player(playerName);
		game.getPlayers().add(player);
	}

	public void setPlayerReady(final int gameId, final String playerName, final boolean ready)
			throws NoSuchGameException, NoSuchPlayerException {

		final Game game = gameDao.getGame(gameId);
		Player player = null;
		try {
			player = game.getPlayers().stream()
					.filter(p -> playerName.equals(p.getName()))
					.findFirst().get();
		} catch (final NoSuchElementException e) {
			throw new NoSuchPlayerException(game.getId(), playerName);
		}
		player.setReady(ready);
	}

	private void validatePlayerJoinGame(final Game game, final String playerName) throws PlayerAlreadyExistsException {
		for (final Player player : game.getPlayers()) {
			if (playerName.equals(player.getName())) {
				throw new PlayerAlreadyExistsException(game.getId(), playerName);
			}
		}
	}

	private void validateGameisJoinable(final Game game) throws GameNotJoinableException {
		if (!game.getPhase().equals(GamePhase.JOIN)) {
			throw new GameNotJoinableException(game.getId());
		}
	}
}
