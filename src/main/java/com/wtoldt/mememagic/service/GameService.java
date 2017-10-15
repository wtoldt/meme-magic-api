package com.wtoldt.mememagic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wtoldt.mememagic.dao.GameDao;

@Service
public class GameService {

	private GameDao gameDao;

	@Autowired
	public GameService(GameDao gameDao) {
		this.gameDao = gameDao;
	}

	public int createGame() {
		return gameDao.createGame();
	}

}
