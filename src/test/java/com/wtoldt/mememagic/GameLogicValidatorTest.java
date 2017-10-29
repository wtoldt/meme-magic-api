package com.wtoldt.mememagic;

import org.junit.Test;

import com.wtoldt.mememagic.domain.Game;
import com.wtoldt.mememagic.domain.GamePhase;
import com.wtoldt.mememagic.domain.Player;
import com.wtoldt.mememagic.exception.GameNotJoinableException;
import com.wtoldt.mememagic.exception.PlayerAlreadyExistsException;
import com.wtoldt.mememagic.validator.GameLogicValidator;



public class GameLogicValidatorTest {

	@Test
	public void testValidatePlayerJoinGame() throws PlayerAlreadyExistsException {
		final Game game = new Game(0);
		GameLogicValidator.validatePlayerJoinGame(game, "foo");
	}

	@Test(expected = PlayerAlreadyExistsException.class)
	public void testValidatePlayerJoinGameForPlayerAlreadyExistsException() throws PlayerAlreadyExistsException {
		final Game game = new Game(0);
		final Player player = new Player("foo");
		game.getPlayers().add(player);
		GameLogicValidator.validatePlayerJoinGame(game, "foo");
	}

	@Test
	public void testValidateGameisJoinable() throws GameNotJoinableException {
		final Game game = new Game(0);
		GameLogicValidator.validateGameisJoinable(game);
	}

	@Test(expected = GameNotJoinableException.class)
	public void testValidGameisJoinableForGameNotJoinableException() throws GameNotJoinableException {
		final Game game = new Game(0);
		game.setPhase(GamePhase.CAPTION_IMAGES);
		GameLogicValidator.validateGameisJoinable(game);
	}

}
