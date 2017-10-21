package com.wtoldt.mememagic.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wtoldt.mememagic.domain.Game;
import com.wtoldt.mememagic.domain.Player;
import com.wtoldt.mememagic.service.GameService;

@RestController
@RequestMapping("api/v1/games")
public class GameApiController {

	private final GameService gameService;

	@Autowired
	public GameApiController(final GameService gameService) {
		this.gameService = gameService;
	}

	@RequestMapping("hello")
	public String helloWorld() {
		return "world";
	}

	@RequestMapping(value="", method=RequestMethod.POST)
	public int createGame() {
		return gameService.createGame();
	}

	@RequestMapping(value="/{gameId}/players", method=RequestMethod.POST)
	public String joinGame(@PathVariable final int gameId, @Valid final String playerName) {

		// TODO: put the player in the game
		final String response = "success";

		return response;
	}

	@RequestMapping(value="/{gameId}", method=RequestMethod.GET)
	public Game getGame(@PathVariable final int gameId) {
		return gameService.getGame(gameId);
	}

}
