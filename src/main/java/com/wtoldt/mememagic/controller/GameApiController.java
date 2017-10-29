package com.wtoldt.mememagic.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wtoldt.mememagic.domain.ApiResponse;
import com.wtoldt.mememagic.domain.Game;
import com.wtoldt.mememagic.domain.GameState;
import com.wtoldt.mememagic.domain.JoinGameRequest;
import com.wtoldt.mememagic.domain.PlayerReadyRequest;
import com.wtoldt.mememagic.exception.GameNotJoinableException;
import com.wtoldt.mememagic.exception.NoSuchGameException;
import com.wtoldt.mememagic.exception.NoSuchPlayerException;
import com.wtoldt.mememagic.exception.PlayerAlreadyExistsException;
import com.wtoldt.mememagic.factory.ApiResponseFactory;
import com.wtoldt.mememagic.factory.GameStateFactory;
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
	public ApiResponse createGame() {
		final int gameId = gameService.createGame();

		final ApiResponse response = ApiResponseFactory.successful();
		response.setValue("gameId", gameId);
		return response;
	}

	@RequestMapping(value="/{gameId}/players", method=RequestMethod.POST)
	public ApiResponse joinGame(
			@PathVariable final int gameId,
			@Valid final JoinGameRequest joinGameRequest)
					throws NoSuchGameException, GameNotJoinableException, PlayerAlreadyExistsException {

		final String playerName = joinGameRequest.getPlayerName();
		gameService.joinGame(gameId, playerName);

		final ApiResponse response = ApiResponseFactory.successful();
		response.setValue("message", String.format("Player %s has joined the game successfully!", playerName));
		return response;
	}

	@RequestMapping(value = "/{gameId}/players/{playerName}", method = RequestMethod.PUT)
	public ApiResponse playerReady(@PathVariable final int gameId, @PathVariable final String playerName,
			final PlayerReadyRequest playerReadyRequest)
					throws NoSuchGameException, NoSuchPlayerException {

		gameService.setPlayerReady(gameId, playerName, playerReadyRequest.getReady());

		final ApiResponse response = ApiResponseFactory.successful();
		response.setValue("message", String.format("Player %s has readied up successfully!", playerName));
		return response;
	}

	@RequestMapping(value="/{gameId}", method=RequestMethod.GET)
	public ApiResponse getGameState(@PathVariable final int gameId) throws NoSuchGameException {
		final Game game = gameService.getGame(gameId);
		final GameState gameState = GameStateFactory.fromGame(game);

		final ApiResponse response = ApiResponseFactory.successful();
		response.setValue("gameState", gameState);
		return response;
	}

}
