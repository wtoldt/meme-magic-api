package com.wtoldt.mememagic.service;

import com.wtoldt.mememagic.domain.Player;
import com.wtoldt.mememagic.exception.NoSuchGameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtoldt.mememagic.dao.GameDao;
import com.wtoldt.mememagic.domain.Game;

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
		Game game = gameDao.getGame(gameId);
		if (game == null) {
			throw new NoSuchGameException("Player '" + playerName + "' cannot join game #" + gameId + " as the game does not exist");
		}

		Player player = new Player(playerName);
		game.getPlayers().add(player);
    }
}
