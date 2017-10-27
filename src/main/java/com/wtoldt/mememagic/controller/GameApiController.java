package com.wtoldt.mememagic.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wtoldt.mememagic.domain.Game;
import com.wtoldt.mememagic.domain.GameState;
import com.wtoldt.mememagic.domain.JoinGameRequest;
import com.wtoldt.mememagic.domain.PlayerReadyRequest;
import com.wtoldt.mememagic.exception.GameNotJoinableException;
import com.wtoldt.mememagic.exception.NoSuchGameException;
import com.wtoldt.mememagic.exception.PlayerAlreadyExistsException;
import com.wtoldt.mememagic.service.GameService;

@Validated
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
	public String joinGame(
			@PathVariable final int gameId,
			@Valid final JoinGameRequest joinGameRequest)
					throws NoSuchGameException, GameNotJoinableException, PlayerAlreadyExistsException {

		gameService.joinGame(gameId, joinGameRequest.getPlayer());
		final String response = "success";

		return response;
	}
	
	@RequestMapping(value="/{gameId}/players", method=RequestMethod.PUT)
	public String playerReady(
			@PathVariable final int gameId,
			@Valid final PlayerReadyRequest playerReadyRequest)
					throws NoSuchGameException, GameNotJoinableException, PlayerAlreadyExistsException {

		gameService.joinGame(gameId, playerReadyRequest.getPlayer());
		final String response = "success";

		return response;
	}

	@RequestMapping(value="/{gameId}", method=RequestMethod.GET)
	public GameState getGameState(@PathVariable final int gameId) throws NoSuchGameException {
		final Game game = gameService.getGame(gameId);
		return GameService.getGameState(game);
	}

}
