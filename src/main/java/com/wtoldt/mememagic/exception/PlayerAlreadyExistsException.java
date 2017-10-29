package com.wtoldt.mememagic.exception;

public class PlayerAlreadyExistsException extends GameException {
	private static final long serialVersionUID = 6800459128439767303L;

	public PlayerAlreadyExistsException(final int gameId, final String playerName) {
		super(String.format("Player %s already exists in game %s!", playerName, gameId));
	}

}
