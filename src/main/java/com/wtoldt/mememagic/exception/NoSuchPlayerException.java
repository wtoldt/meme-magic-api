package com.wtoldt.mememagic.exception;

public class NoSuchPlayerException extends GameException {
	private static final long serialVersionUID = 7895676559571076911L;

	public NoSuchPlayerException(final int gameId, final String playerName) {
		super(String.format("Player %s does not exist in game %s!", gameId, playerName));
	}

}
