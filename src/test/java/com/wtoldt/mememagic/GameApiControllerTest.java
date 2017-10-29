package com.wtoldt.mememagic;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.IntStream;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.wtoldt.mememagic.config.MemeMagicApiConfig;
import com.wtoldt.mememagic.controller.GameApiController;
import com.wtoldt.mememagic.domain.GameState;
import com.wtoldt.mememagic.domain.JoinGameRequest;

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

		final GameState gameState = (GameState) gameApiController.getGameState(gameId).get("gameState");
		final List<String> players = gameState.getPlayers();

		assertEquals(1, players.size());
		assertEquals(playerName, players.get(0));

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
