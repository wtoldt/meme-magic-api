package com.wtoldt.mememagic.controller;

import com.wtoldt.mememagic.config.MemeMagicApiConfig;
import com.wtoldt.mememagic.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by Emily Li on 21/10/2017.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {MemeMagicApiConfig.class})
public class GameApiControllerTest {
	@Autowired
	private GameApiController gameApiController;

	@Test
	public void testJoinGame() throws Exception {
		final String playerName = "player";
		final int gameId = createAndJoinGame(playerName);

		final GameState gameState = (GameState) gameApiController.getGameState(gameId).get("game");
		final List<PlayerState> players = gameState.getPlayers();

		assertEquals(1, players.size());
		assertEquals(playerName, players.get(0).getName());

		// TODO: 4. gameId should be validated - it should be open
	}

	@Test(expected = ConstraintViolationException.class)
	public void testInvalidPlayerNameValidation() throws Exception {
		testJoinGameNameLimit(51);
	}

	@Test
	public void testValidPlayerNameValidation() throws Exception {
		testJoinGameNameLimit(50);
	}

	@Test
	public void testGameState() throws Exception {
		final ApiResponse createGameResponse = gameApiController.createGame();
		final int gameId = (int) createGameResponse.get("gameId");
		final JoinGameRequest joinGameRequest = new JoinGameRequest();
		joinGameRequest.setPlayerName("asdf");
		final PlayerReadyRequest playerReadyRequest = new PlayerReadyRequest();
		playerReadyRequest.setReady(true);

		gameApiController.joinGame(gameId, joinGameRequest);
		gameApiController.playerReady(gameId, joinGameRequest.getPlayerName(), playerReadyRequest);

		final ApiResponse gameStateResponse = gameApiController.getGameState(gameId);
		final GameState gameState = (GameState) gameStateResponse.get("game");
		assertEquals(true, gameState.getPlayers().get(0).isReady());
	}

	private void testJoinGameNameLimit(final int nameLength) throws Exception {
		final StringBuilder playerName = new StringBuilder();
		IntStream.range(0, nameLength).forEach(i -> playerName.append('.'));
		createAndJoinGame(playerName.toString());
	}

	private int createAndJoinGame(final String playerName) throws Exception {
		final int gameId = (int) gameApiController.createGame().get("gameId");
		final JoinGameRequest joinGameRequest = new JoinGameRequest();
		joinGameRequest.setPlayerName(playerName);
		gameApiController.joinGame(gameId, joinGameRequest);
		return gameId;
	}
}
