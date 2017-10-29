package com.wtoldt.mememagic.factory;

import java.util.ArrayList;
import java.util.List;

import com.wtoldt.mememagic.domain.Game;
import com.wtoldt.mememagic.domain.GameState;
import com.wtoldt.mememagic.domain.Player;
import com.wtoldt.mememagic.domain.PlayerState;

public class GameStateFactory {

	public static GameState fromGame(final Game game) {
		final GameState gameState = new GameState();

		gameState.getPlayers().addAll(getPlayerStates(game.getPlayers()));
		gameState.setReady(game.isReady());
		gameState.setPhase(game.getPhase());

		return gameState;
	}

	private static List<PlayerState> getPlayerStates(final List<Player> players) {
		final List<PlayerState> playerStates = new ArrayList<>();
		for (final Player player : players) {
			final PlayerState playerState = new PlayerState();
			playerState.setName(player.getName());
			playerState.setScore(player.getScore());
			playerState.setReady(player.isReady());
			playerStates.add(playerState);
		}
		return playerStates;
	}

}
