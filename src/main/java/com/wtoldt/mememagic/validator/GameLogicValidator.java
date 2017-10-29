package com.wtoldt.mememagic.validator;

import com.wtoldt.mememagic.domain.Game;
import com.wtoldt.mememagic.domain.GamePhase;
import com.wtoldt.mememagic.domain.Player;
import com.wtoldt.mememagic.exception.GameNotJoinableException;
import com.wtoldt.mememagic.exception.PlayerAlreadyExistsException;

public class GameLogicValidator {
	public static void validatePlayerJoinGame(final Game game, final String playerName) throws PlayerAlreadyExistsException {
		for (final Player player : game.getPlayers()) {
			if (playerName.equals(player.getName())) {
				throw new PlayerAlreadyExistsException(game.getId(), playerName);
			}
		}
	}

	public static void validateGameisJoinable(final Game game) throws GameNotJoinableException {
		if (!game.getPhase().equals(GamePhase.JOIN)) {
			throw new GameNotJoinableException(game.getId());
		}
	}
}
