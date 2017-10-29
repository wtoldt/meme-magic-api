package com.wtoldt.mememagic.factory;

import java.util.stream.Collectors;

import com.wtoldt.mememagic.domain.Game;
import com.wtoldt.mememagic.domain.GameState;
import com.wtoldt.mememagic.domain.Player;

public class GameStateFactory {

	public static GameState fromGame(final Game game) {
		final GameState gameState = new GameState();

		gameState.getPlayers().addAll(game.getPlayers().stream().map(Player::getName).collect(Collectors.toList()));
		gameState.setReady(game.isReady());
		gameState.setPhase(game.getPhase());

		return gameState;
	}

}
