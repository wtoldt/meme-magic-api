package com.wtoldt.mememagic.controller;

import com.wtoldt.mememagic.domain.*;
import com.wtoldt.mememagic.exception.GameNotJoinableException;
import com.wtoldt.mememagic.exception.NoSuchGameException;
import com.wtoldt.mememagic.exception.NoSuchPlayerException;
import com.wtoldt.mememagic.exception.PlayerAlreadyExistsException;
import com.wtoldt.mememagic.factory.ApiResponseFactory;
import com.wtoldt.mememagic.factory.GameStateFactory;
import com.wtoldt.mememagic.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("api/v1/games")
class GameApiController {

	private final GameService gameService;

	@Autowired
    GameApiController(final GameService gameService) {
        this.gameService = gameService;
	}

	@RequestMapping(value="", method=RequestMethod.POST)
    ApiResponse createGame(final CreateGameRequest createGameRequest) {
        final int gameId = gameService.createGame(createGameRequest);

		final ApiResponse response = ApiResponseFactory.successful();
		response.setValue("gameId", gameId);
		return response;
	}

	@RequestMapping(value="/{gameId}/players", method=RequestMethod.POST)
    ApiResponse joinGame(
            @PathVariable final int gameId,
			@RequestBody @Valid final JoinGameRequest joinGameRequest)
					throws NoSuchGameException, GameNotJoinableException, PlayerAlreadyExistsException {

		final String playerName = joinGameRequest.getPlayerName();
		gameService.joinGame(gameId, playerName);

		final ApiResponse response = ApiResponseFactory.successful();
		response.setValue("message", String.format("Player %s has joined the game successfully!", playerName));
		return response;
	}

	@RequestMapping(value = "/{gameId}/players/{playerName}", method = RequestMethod.PUT)
    ApiResponse playerReady(@PathVariable final int gameId,
                            @PathVariable final String playerName,
                            @RequestBody final PlayerReadyRequest playerReadyRequest)
					throws NoSuchGameException, NoSuchPlayerException {

		final boolean playerReady = playerReadyRequest.getReady();
		gameService.setPlayerReady(gameId, playerName, playerReady);

		final ApiResponse response = ApiResponseFactory.successful();

		final String readyVerb = playerReady ? "readied" : "unreadied";
		response.setValue("message", String.format("Player %s has %s successfully!", playerName, readyVerb));

		return response;
	}

	@RequestMapping(value="/{gameId}", method=RequestMethod.GET)
    ApiResponse getGameState(@PathVariable final int gameId) throws NoSuchGameException {
        final Game game = gameService.getGame(gameId);
		final GameState gameState = GameStateFactory.fromGame(game);

		final ApiResponse response = ApiResponseFactory.successful();
		response.setValue("game", gameState);
		return response;
	}

}
