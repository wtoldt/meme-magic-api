package com.wtoldt.mememagic.service;

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

}
