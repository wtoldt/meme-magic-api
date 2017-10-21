package com.wtoldt.mememagic.controller;

import javax.validation.Valid;

import com.wtoldt.mememagic.exception.NoSuchGameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

	@RequestMapping(value="", method=RequestMethod.POST)
	public int createGame() {
		return gameService.createGame();
	}

	@RequestMapping(value="/{gameId}/players", method=RequestMethod.POST)
	public String joinGame(@PathVariable final int gameId,
						   @RequestParam final String playerName) throws NoSuchGameException {
		gameService.joinGame(gameId, playerName);
		// TODO: put the player in the game
		final String response = "success";

		return response;
	}

	@RequestMapping(value="/{gameId}", method=RequestMethod.GET)
	public Game getGame(@PathVariable final int gameId) {
		return gameService.getGame(gameId);
	}

}
